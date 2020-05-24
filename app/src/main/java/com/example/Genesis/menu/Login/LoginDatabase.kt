package com.example.Genesis.menu.Login

import android.content.Context
import android.database.Cursor
import com.example.Genesis.database.Database
import com.example.Genesis.menu.Login.LoginContract.LoginDatabaseInterface

class LoginDatabase(context : Context) : LoginDatabaseInterface{
    var db : Database
    init{
        Database.db = Database(context)
        db = Database.db
        Database.dbw = Database.db.writableDatabase
        Database.dbr = Database.db.readableDatabase
        db.initQuestGenerator()
    }
    override fun checkUserName(username: String): Boolean {
        var usernameQuery : String = "SELECT " + Database.KEY_ACCOUNT_USERNAME + " FROM " + Database.TABLE_ACCOUNT +
                " WHERE " + Database.KEY_ACCOUNT_USERNAME + " = '" + username + "';"
        var cursor : Cursor = Database.dbw.rawQuery(usernameQuery,null)
        cursor.moveToNext()
        if(cursor.count <= 0) {
            return false
        }
        cursor.close()
        return true
    }

    override fun checkPassWord(username : String,password: String): Boolean {
        var passwordQuery : String = "SELECT " + Database.KEY_ACCOUNT_PASSWORD + " FROM " + Database.TABLE_ACCOUNT +
                " WHERE " + Database.KEY_ACCOUNT_USERNAME + " = '" + username +
                "' AND " + Database.KEY_ACCOUNT_PASSWORD + " = '" + password + "';"
        var cursor = Database.dbw.rawQuery(passwordQuery,null)
        cursor.moveToNext()
        if(cursor.count <= 0) {
            return false
        }
        cursor.close()
        return true
    }

    override fun getAccountID(username: String): Int {
        var passwordQuery : String = "SELECT " + Database.KEY_ACCOUNT_ID + " FROM " + Database.TABLE_ACCOUNT +
                " WHERE " + Database.KEY_ACCOUNT_USERNAME + " = '" + username + "';"
        var cursor = Database.dbw.rawQuery(passwordQuery,null)
        cursor.moveToNext()
        if(cursor.count <= 0) {
            return -1
        }
        var value : Int = cursor.getInt(cursor.getColumnIndex(Database.KEY_ACCOUNT_ID))
        cursor.close()
        return value
    }
}