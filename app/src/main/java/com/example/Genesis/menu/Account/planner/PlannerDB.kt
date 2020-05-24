package com.example.Genesis.menu.Account.planner

import android.content.ContentValues
import android.database.Cursor
import com.example.Genesis.database.Database
import com.example.Genesis.objects.Quest
import com.example.Genesis.user.Account.Account
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Attributes
import kotlin.collections.ArrayList

class PlannerDB(){

    fun insertEvent(event : EventDesc){
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_EVENT_NAME,event.name)
        cv.put(Database.KEY_EVENT_DESCRIPTION,event.description)
        cv.put(Database.KEY_EVENT_NOTES, event.notes)
        cv.put(Database.KEY_EVENT_DATE, event.date)
        cv.put(Database.KEY_EVENT_ACCOUNT, Account.accountNumber)
        var re : Long = Database.dbw.insert(Database.TABLE_EVENT,null,cv)
    }

    fun updateEvent(event : EventDesc){
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_EVENT_NAME,event.name)
        cv.put(Database.KEY_EVENT_DESCRIPTION,event.description)
        cv.put(Database.KEY_EVENT_NOTES, event.notes)
        cv.put(Database.KEY_EVENT_DATE, event.date)
        cv.put(Database.KEY_EVENT_ACCOUNT, Account.accountNumber)
        var re : Int = Database.dbw.update(Database.TABLE_EVENT,cv,Database.KEY_EVENT_DATE + " = '" + event.date + "'",null)
    }

    fun getFullEvent(eventName : String): EventDesc {
        var query: String =
            "SELECT * FROM " + Database.TABLE_EVENT + " WHERE " + Database.KEY_EVENT_NAME + " = '" + eventName + "'"
        var cursor: Cursor = Database.dbw.rawQuery(query, null)
        var event: EventDesc = EventDesc(0,"", "", "", "")
        while (cursor.moveToNext()) {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_EVENT_ID))
            var accountid = cursor.getInt(cursor.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_NAME))
            var description = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_DESCRIPTION))
            var notes = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_NOTES))
            var date = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_DATE))
            event = EventDesc(id,name, description, notes, date)
        }
        return event
    }

    fun getAllEvent() : ArrayList<EventDesc> {
        var query: String = "SELECT * FROM " + Database.TABLE_EVENT
        var cursor: Cursor = Database.dbw.rawQuery(query, null)
        var event: EventDesc = EventDesc(0,"", "", "", "")
        var eventList: ArrayList<EventDesc> = ArrayList()
        while (cursor.moveToNext()) {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_EVENT_ID))
            var accountid = cursor.getInt(cursor.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_NAME))
            var description =
                cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_DESCRIPTION))
            var notes = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_NOTES))
            var date = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_DATE))
            event = EventDesc(id,name, description, notes, date)
            eventList.add(event)
        }
        return eventList
    }

    fun getEvent(eventDate : String) : EventDesc{
        var query: String =
            "SELECT * FROM " + Database.TABLE_EVENT + " WHERE " + Database.KEY_EVENT_DATE + " = '" + eventDate + "'"
        var cursor: Cursor = Database.dbw.rawQuery(query, null)
        var event: EventDesc = EventDesc(0,"", "", "", "")
        while (cursor.moveToNext()) {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_EVENT_ID))
            var accountid = cursor.getInt(cursor.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_NAME))
            var description = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_DESCRIPTION))
            var notes = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_NOTES))
            var date = cursor.getString(cursor.getColumnIndex(Database.KEY_EVENT_DATE))
            event = EventDesc(id,name, description, notes, date)
        }
        return event
    }

    fun deleteEvent(eventID : Int){
        Database.dbw.delete(Database.TABLE_EVENT,Database.KEY_EVENT_ID + " = " + eventID, null)
    }
}