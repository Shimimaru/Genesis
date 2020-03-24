package com.example.Genesis.user.Player

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.Genesis.database.Database

class PlayerDatabase(var player : Player) : PlayerContract.PlayerDatabaseInterface {

    lateinit var db : Database
    lateinit var dbw : SQLiteDatabase
    lateinit var dbr : SQLiteDatabase

    init{
        db = Database.db
        dbw = db.writableDatabase
        dbr = db.readableDatabase
    }

    override fun getPlayer(accNum : Int) : Player
    {
        println("Error")
        var playerQuery : String = "SELECT * FROM " + Database.TABLE_PLAYER + " WHERE " + Database.KEY_PLAYER_ID + " = " + accNum
        var cursor : Cursor = dbw.rawQuery(playerQuery,null)
        cursor.moveToNext()
        player.level = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_LEVEL))
        player.levelExperienceValue = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_LEVEL_EXPERIENCE_VALUE))
        player.levelExperienceMax = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_LEVEL_EXPERIENCE_MAX))
        player.strength = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_STRENGTH))
        player.strengthExperienceValue = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_STRENGTH_EXPERIENCE_VALUE))
        player.strengthExperienceMax = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_STRENGTH_EXPERIENCE_MAX))
        player.agility = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_AGILITY))
        player.agilityExperienceValue = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_AGILITY_EXPERIENCE_VALUE))
        player.agilityExperienceMax = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_AGILITY_EXPERIENCE_MAX))
        player.endurance = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_ENDURANCE))
        player.enduranceExperienceValue = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_ENDURANCE_EXPERIENCE_VALUE))
        player.enduranceExperienceMax = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_ENDURANCE_EXPERIENCE_MAX))
        player.intelligence = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_INTELLIGENCE))
        player.intelligenceExperienceValue = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_INTELLIGENCE_EXPERIENCE_VALUE))
        player.intelligenceExperienceMax = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_INTELLIGENCE_EXPERIENCE_MAX))
        player.wisdom = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_WISDOM))
        player.wisdomExperienceValue = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_INTELLIGENCE_EXPERIENCE_VALUE))
        player.wisdomExperienceMax = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_WISDOM_EXPERIENCE_MAX))
        player.charisma = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_CHARISMA))
        player.charismaExperienceValue = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_CHARISMA_EXPERIENCE_VALUE))
        player.charismaExperienceMax = cursor.getInt(cursor.getColumnIndex(Database.KEY_PLAYER_CHARISMA_EXPERIENCE_MAX))
        cursor.close()
        println("Error")
        return player
    }

    override fun updatePlayer(accNum : Int) : Boolean{
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_PLAYER_ID,accNum)
        //Level
        cv.put(Database.KEY_PLAYER_LEVEL,player.level)
        cv.put(Database.KEY_PLAYER_LEVEL_EXPERIENCE_VALUE,player.levelExperienceValue)
        cv.put(Database.KEY_PLAYER_LEVEL_EXPERIENCE_MAX,player.levelExperienceMax)
        //Strength
        cv.put(Database.KEY_PLAYER_STRENGTH,player.strength)
        cv.put(Database.KEY_PLAYER_STRENGTH_EXPERIENCE_VALUE,player.strengthExperienceValue)
        cv.put(Database.KEY_PLAYER_STRENGTH_EXPERIENCE_MAX,player.strengthExperienceMax)
        //Agility
        cv.put(Database.KEY_PLAYER_AGILITY,player.agility)
        cv.put(Database.KEY_PLAYER_AGILITY_EXPERIENCE_VALUE,player.agilityExperienceValue)
        cv.put(Database.KEY_PLAYER_AGILITY_EXPERIENCE_MAX,player.agilityExperienceMax)
        //Endurance
        cv.put(Database.KEY_PLAYER_ENDURANCE,player.endurance)
        cv.put(Database.KEY_PLAYER_ENDURANCE_EXPERIENCE_VALUE,player.enduranceExperienceValue)
        cv.put(Database.KEY_PLAYER_ENDURANCE_EXPERIENCE_MAX,player.enduranceExperienceMax)
        //Intelligence
        cv.put(Database.KEY_PLAYER_INTELLIGENCE,player.intelligence)
        cv.put(Database.KEY_PLAYER_INTELLIGENCE_EXPERIENCE_VALUE,player.intelligenceExperienceValue)
        cv.put(Database.KEY_PLAYER_INTELLIGENCE_EXPERIENCE_MAX,player.intelligenceExperienceMax)
        //Wisdom
        cv.put(Database.KEY_PLAYER_WISDOM,player.wisdom)
        cv.put(Database.KEY_PLAYER_WISDOM_EXPERIENCE_VALUE,player.wisdomExperienceValue)
        cv.put(Database.KEY_PLAYER_WISDOM_EXPERIENCE_MAX,player.wisdomExperienceMax)
        //Charisma
        cv.put(Database.KEY_PLAYER_CHARISMA,player.charisma)
        cv.put(Database.KEY_PLAYER_CHARISMA_EXPERIENCE_VALUE,player.charismaExperienceValue)
        cv.put(Database.KEY_PLAYER_CHARISMA_EXPERIENCE_MAX,player.charismaExperienceMax)
        var cursor : Cursor = dbw.rawQuery("SELECT * FROM " + Database.TABLE_PLAYER + " WHERE " + Database.KEY_PLAYER_ID + " = " + accNum,null )
        if(cursor.count <= 0)
        {
            val re : Long = dbw.insert(Database.TABLE_PLAYER,null,cv)
            if(re <= 0)
                return false
        }
        else {
            val re : Int = dbw.update(
                Database.TABLE_PLAYER,
                cv,
                Database.KEY_PLAYER_ID + " = " + accNum,
                null
            )
            if(re <= 0)
                return false
        }
        cursor.close()
        return true
    }
}