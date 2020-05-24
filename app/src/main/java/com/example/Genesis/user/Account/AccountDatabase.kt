package com.example.Genesis.user.Account

import android.content.ContentValues
import android.database.Cursor
import com.example.Genesis.database.Database
import com.example.Genesis.user.Person.Person
import com.example.Genesis.user.Player.Player

class AccountDatabase(var account : Account) : AccountContract.AccountDatabaseInterface{

    override fun insertAccount(person: Person, player : Player) : Boolean
    {
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_ACCOUNT_USERNAME,account.username)
        cv.put(Database.KEY_ACCOUNT_PASSWORD,account.password)
        cv.put(Database.KEY_ACCOUNT_QUEST,0)
        var re : Long = Database.dbw.insert(Database.TABLE_ACCOUNT,null,cv)
        if(-1L == re)
            return false
        var query : String = "SELECT " + Database.KEY_ACCOUNT_ID + " FROM " + Database.TABLE_ACCOUNT + " ORDER BY ID DESC LIMIT 1"
        var cursor : Cursor = Database.dbw.rawQuery(query,null)
        var value : Int = 0
        while(cursor.moveToNext())
        {
            value = cursor.getInt(cursor.getColumnIndex(Database.KEY_ACCOUNT_ID))
            Account.accountNumber = value
            account.accNum = value
        }
        cv.put(Database.KEY_ACCOUNT_PERSONID,value)
        cv.put(Database.KEY_ACCOUNT_PLAYERID,value)
        player.playerID = value;
        Database.dbw.update(Database.TABLE_ACCOUNT,cv, Database.KEY_ACCOUNT_ID + " = ?" + value,null)
        return true
    }

    fun getAccount(accNum : Int) : Account
    {
        var accountQuery : String = "SELECT * FROM " + Database.TABLE_ACCOUNT + " WHERE " + Database.KEY_ACCOUNT_ID + " = " + accNum
        var cursor : Cursor = Database.dbw.rawQuery(accountQuery,null)
        cursor.moveToNext()
        account.username = cursor.getString(cursor.getColumnIndex(Database.KEY_ACCOUNT_USERNAME))
        account.password = cursor.getString(cursor.getColumnIndex(Database.KEY_ACCOUNT_PASSWORD))
        account.player = Account.player.playerDB.getPlayer(accNum)
        account.person = Account.person.personDB.getPerson(accNum)
        return account
    }
}