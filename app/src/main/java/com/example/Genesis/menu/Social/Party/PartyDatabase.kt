package com.example.Genesis.menu.Social
import android.content.ContentValues
import android.database.Cursor
import com.example.Genesis.database.Database
import com.example.Genesis.menu.Social.Party.PartyContract.*
import com.example.Genesis.menu.Social.Party.PartyCreateMenu
import com.example.Genesis.menu.Social.Party.PartyMenu
import com.example.Genesis.objects.Party
import com.example.Genesis.user.Account.Account
import java.io.Serializable

class PartyDatabase  : PartyDatabaseInterface,Serializable {

   constructor(party : PartyCreateMenu)
   constructor(party: PartyMenu)


    override fun getParty(): ArrayList<Party> {
        var query : String = "SELECT * FROM " + Database.TABLE_PARTY + " WHERE " + Database.KEY_PARTY_ACCOUNT_ID+ " = " + Account.accountNumber
        var cursor : Cursor = Database.dbw.rawQuery(query,null)
        var partyList : ArrayList<Party> = ArrayList<Party>()
        partyList.clear()
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_PARTY_ID))
            var accountid = cursor.getInt(cursor.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_PARTY_NAME))
            var description = cursor.getString(cursor.getColumnIndex(Database.KEY_PARTY_DESCRIPTION))
            var party : Party = Party(id,name,description)
            partyList.add(party)
        }
        return partyList
    }

    fun getGeneratedParty()
    {
        var query : String = "SELECT * FROM " + Database.TABLE_PARTY_GENERATED
        var cursor : Cursor = Database.dbw.rawQuery(query,null)
        var partyList : ArrayList<Party> = ArrayList<Party>()
        partyList.clear()
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_PARTY_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_PARTY_NAME))
            var description = cursor.getString(cursor.getColumnIndex(Database.KEY_PARTY_DESCRIPTION))

            var party : Party = Party(id,name,description)
            partyList.add(party)
        }
        var number = (0 until partyList.size).random()
        insertParty(partyList.get(number))

    }

    override fun insertParty(party: Party) {
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_PARTY_NAME,party.name)
        cv.put(Database.KEY_PARTY_DESCRIPTION,party.description)
        cv.put(Database.KEY_PARTY_ACCOUNT_ID,Account.accountNumber)
        var re : Long = Database.dbw.insert(Database.TABLE_PARTY,null,cv)
        PartyMenu.arrayAdapter.add(party.name)
        PartyMenu.arrayAdapter.notifyDataSetChanged()
        PartyMenu.partyList.add(party)
    }

    override fun updateParty() {
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_ACCOUNT_PARTY,PartyMenu.trackedParty)
        Database.dbw.update(Database.TABLE_ACCOUNT,cv,Database.KEY_ACCOUNT_ID + " = " + Account.accountNumber,null)
    }

    override fun deleteParty(partyID: Int) {
        Database.dbw.delete(Database.TABLE_PARTY,Database.KEY_PARTY_ID + " = " + partyID, null)
    }

    override fun getTrackedParty(): Party {
        var queryAccount : String = "SELECT * FROM " + Database.TABLE_ACCOUNT + " WHERE " + Database.KEY_ACCOUNT_ID + " = " + Account.accountNumber
        var cursorAccount : Cursor = Database.dbw.rawQuery(queryAccount,null)
        var trackID : Int = 0
        var partyList : ArrayList<Party> = ArrayList<Party>()
        partyList.clear()
        while(cursorAccount.moveToNext())
        {
            trackID = cursorAccount.getInt(cursorAccount.getColumnIndex(Database.KEY_ACCOUNT_PARTY))
        }
        var queryParty : String = "SELECT * FROM " + Database.TABLE_PARTY + " WHERE " + Database.KEY_PARTY_ID + " = " + trackID
        var cursorParty : Cursor = Database.dbw.rawQuery(queryParty,null)
        var party : Party = Party (0,"","")
        while(cursorParty.moveToNext())
        {
            var id = cursorParty.getInt(cursorParty.getColumnIndex(Database.KEY_PARTY_ID))
            var accountid = cursorParty.getInt(cursorParty.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursorParty.getString(cursorParty.getColumnIndex(Database.KEY_PARTY_NAME))
            var description = cursorParty.getString(cursorParty.getColumnIndex(Database.KEY_PARTY_DESCRIPTION))

            party = Party(id,name,description)
        }
        return party
    }
    }
