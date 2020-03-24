package com.example.Genesis.user.Person

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.Genesis.database.Database
import com.example.Genesis.user.Person.PersonContract.PersonDatabaseInterface

class PersonDatabase(var person : Person) : PersonDatabaseInterface{
    var db : Database
    var dbw : SQLiteDatabase
    var dbr : SQLiteDatabase

    init{
        db = Database.db
        dbw = db.writableDatabase
        dbr = db.readableDatabase
    }

    override fun updatePerson(accNum: Int) : Boolean{
        var cv : ContentValues = ContentValues()

        cv.put(Database.KEY_PERSON_ID,accNum)
        cv.put(Database.KEY_PERSON_FIRSTNAME,person.firstName)
        cv.put(Database.KEY_PERSON_LASTNAME,person.lastName)
        cv.put(Database.KEY_PERSON_EMAIL,person.email)
        cv.put(Database.KEY_PERSON_PHONE,person.phone)

        var cursor : Cursor = dbw.rawQuery("SELECT * FROM " + Database.TABLE_PERSON + " WHERE " + Database.KEY_PERSON_ID + " = " + accNum,null )
        if(cursor.count <= 0)
        {
            val re : Long = dbw.insert(Database.TABLE_PERSON,null,cv)
            if(re <= 0)
                return false
        }
        else {
            val re : Int = dbw.update(
                Database.TABLE_PERSON,
                cv,
                Database.KEY_PERSON_ID + " = " + accNum,
                null
            )
            if(re <= 0)
                return false
        }
        cursor.close()
        return true
    }

    override fun getPerson(accNum: Int) : Person{
        var playerQuery : String = "SELECT * FROM " + Database.TABLE_PERSON + " WHERE " + Database.KEY_PERSON_ID + " = " + accNum
        var cursor : Cursor = dbw.rawQuery(playerQuery,null)
        cursor.moveToNext()
        person.firstName = cursor.getString(cursor.getColumnIndex(Database.KEY_PERSON_FIRSTNAME))
        person.lastName = cursor.getString(cursor.getColumnIndex(Database.KEY_PERSON_LASTNAME))
        person.email = cursor.getString(cursor.getColumnIndex(Database.KEY_PERSON_EMAIL))
        person.phone = cursor.getString(cursor.getColumnIndex(Database.KEY_PERSON_PHONE))
        cursor.close()
        return person
    }
}