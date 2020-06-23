package com.example.Genesis.menu.Quest

import android.content.ContentValues
import android.database.Cursor
import com.example.Genesis.database.Database
import com.example.Genesis.menu.Quest.QuestMenu.QuestMenu
import com.example.Genesis.menu.Quest.QuestContract.*
import com.example.Genesis.menu.Quest.QuestsCreate.QuestCreateMenu
import com.example.Genesis.objects.Quest
import com.example.Genesis.user.Account.Account
import com.google.android.gms.maps.model.LatLng
import java.io.Serializable
import kotlin.random.Random

class QuestDatabase() : QuestDatabaseInterface,Serializable {

    constructor(quest : QuestMenu) : this() {

    }

    constructor(quest : QuestCreateMenu) : this() {

    }

    override fun getQuest() : ArrayList<Quest>{
        System.out.println(Account.accountNumber)
        var query : String = "SELECT * FROM " + Database.TABLE_QUEST + " WHERE " + Database.KEY_QUEST_ACCOUNT_ID + " = " + Account.accountNumber
        var cursor : Cursor = Database.dbw.rawQuery(query,null)
        var questList : ArrayList<Quest> = ArrayList<Quest>()
        questList.clear()
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_QUEST_ID))
            var accountid = cursor.getInt(cursor.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_QUEST_NAME))
            var description = cursor.getString(cursor.getColumnIndex(Database.KEY_QUEST_DESCRIPTION))
            var level = cursor.getInt(cursor.getColumnIndex(Database.KEY_QUEST_LEVEL))
            var latitude = cursor.getDouble(cursor.getColumnIndex(Database.KEY_QUEST_LATITUDE))
            var longitude = cursor.getDouble(cursor.getColumnIndex(Database.KEY_QUEST_LONGITUDE))
            var quest : Quest = Quest(id,name,description,level, LatLng(latitude,longitude))
            questList.add(quest)
        }
        return questList
    }

    fun getGeneratedQuest()
    {
        var query : String = "SELECT * FROM " + Database.TABLE_QUEST_GENERATED
        var cursor : Cursor = Database.dbw.rawQuery(query,null)
        var questList : ArrayList<Quest> = ArrayList<Quest>()
        questList.clear()
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_QUEST_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_QUEST_NAME))
            var description = cursor.getString(cursor.getColumnIndex(Database.KEY_QUEST_DESCRIPTION))
            var level = cursor.getInt(cursor.getColumnIndex(Database.KEY_QUEST_LEVEL))
            var latitude = cursor.getDouble(cursor.getColumnIndex(Database.KEY_QUEST_LATITUDE))
            var longitude = cursor.getDouble(cursor.getColumnIndex(Database.KEY_QUEST_LONGITUDE))
            var quest : Quest = Quest(id,name,description,level, LatLng(latitude,longitude))
            questList.add(quest)
        }
        var number = (0 until questList.size).random()
        insertQuest(questList.get(number))
    }

    override fun insertQuest(quest: Quest) {
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_QUEST_NAME,quest.name)
        cv.put(Database.KEY_QUEST_DESCRIPTION,quest.description)
        cv.put(Database.KEY_QUEST_LEVEL,quest.level)
        cv.put(Database.KEY_QUEST_LATITUDE, quest.latlng?.latitude)
        cv.put(Database.KEY_QUEST_LONGITUDE, quest.latlng?.longitude)
        cv.put(Database.KEY_QUEST_ACCOUNT_ID,Account.accountNumber)
        var re : Long = Database.dbw.insert(Database.TABLE_QUEST,null,cv)
        QuestMenu.arrayAdapter.add(quest.name)
        QuestMenu.arrayAdapter.notifyDataSetChanged()
        QuestMenu.questList.add(quest)
    }

    override fun updateTrackQuest() {
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_ACCOUNT_QUEST,QuestMenu.trackedQuest)
        Database.dbw.update(Database.TABLE_ACCOUNT,cv,Database.KEY_ACCOUNT_ID + " = " + Account.accountNumber,null)
    }

    override fun deleteQuest(questID : Int) {
        Database.dbw.delete(Database.TABLE_QUEST,Database.KEY_QUEST_ID + " = " + questID, null)
    }

    override fun getTrackedQuest(): Quest {
        var queryAccount : String = "SELECT * FROM " + Database.TABLE_ACCOUNT + " WHERE " + Database.KEY_ACCOUNT_ID + " = " + Account.accountNumber
        var cursorAccount : Cursor = Database.dbw.rawQuery(queryAccount,null)
        var trackID : Int = 0
        var questList : ArrayList<Quest> = ArrayList<Quest>()
        questList.clear()
        while(cursorAccount.moveToNext())
        {
            trackID = cursorAccount.getInt(cursorAccount.getColumnIndex(Database.KEY_ACCOUNT_QUEST))
        }
        var queryQuest : String = "SELECT * FROM " + Database.TABLE_QUEST + " WHERE " + Database.KEY_QUEST_ID + " = " + trackID
        var cursorQuest : Cursor = Database.dbw.rawQuery(queryQuest,null)
        var quest : Quest = Quest(0,"","",0,LatLng(0.0,0.0))
        while(cursorQuest.moveToNext())
        {
            var id = cursorQuest.getInt(cursorQuest.getColumnIndex(Database.KEY_QUEST_ID))
            var accountid = cursorQuest.getInt(cursorQuest.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursorQuest.getString(cursorQuest.getColumnIndex(Database.KEY_QUEST_NAME))
            var description = cursorQuest.getString(cursorQuest.getColumnIndex(Database.KEY_QUEST_DESCRIPTION))
            var level = cursorQuest.getInt(cursorQuest.getColumnIndex(Database.KEY_QUEST_LEVEL))
            var latitude = cursorQuest.getDouble(cursorQuest.getColumnIndex(Database.KEY_QUEST_LATITUDE))
            var longitude = cursorQuest.getDouble(cursorQuest.getColumnIndex(Database.KEY_QUEST_LONGITUDE))
            quest = Quest(id,name,description,level, LatLng(latitude,longitude))
        }
        return quest
    }

}