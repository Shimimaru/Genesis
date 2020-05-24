package com.example.Genesis.database
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    init {
        Log.d("table", CREATE_TABLE_ACCOUNT)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_ACCOUNT)
        db.execSQL(CREATE_TABLE_PERSON)
        db.execSQL(CREATE_TABLE_PLAYER)
        //  db.execSQL(CREATE_TABLE_INVENTORY)



    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_ACCOUNT'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_PERSON'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_PLAYER'")
        //  db.execSQL("DROP TABLE IF EXISTS '$TABLE_INVENTORY'")


        onCreate(db)
    }

    fun wipeDatabase(context : Context)
    {
        this.close()
        context.deleteDatabase(DATABASE_NAME)
    }
    companion object {
        //Database
        var DATABASE_NAME = "genesis"
        const val DATABASE_VERSION = 1
        lateinit var db : Database
        lateinit var dbw : SQLiteDatabase
        lateinit var dbr : SQLiteDatabase
        //Tables
        const val TABLE_ACCOUNT = "account"
        const val TABLE_PERSON = "person"
        const val TABLE_PLAYER = "player"
        //const val TABLE_INVENTORY = "inventory"

        //Account Columns
        const val KEY_ACCOUNT_ID = "id"
        const val KEY_ACCOUNT_USERNAME = "username"
        const val KEY_ACCOUNT_PASSWORD = "password"
        const val KEY_ACCOUNT_PERSONID = "personID"
        const val KEY_ACCOUNT_PLAYERID = "playerID"
        //Person Columns
        const val KEY_PERSON_ID = "id"
        const val KEY_PERSON_FIRSTNAME = "firstName"
        const val KEY_PERSON_LASTNAME = "lastName"
        const val KEY_PERSON_EMAIL = "email"
        const val KEY_PERSON_PHONE = "phone"
        const val KEY_PERSON_ACCOUNTID = "accountID"
        //Player Columns
        const val KEY_PLAYER_ID = "id"
        const val KEY_PLAYER_LEVEL = "level"
        const val KEY_PLAYER_LEVEL_EXPERIENCE_VALUE = "levelEXPValue"
        const val KEY_PLAYER_LEVEL_EXPERIENCE_MAX = "levelEXPMax"
        const val KEY_PLAYER_STRENGTH = "strength"
        const val KEY_PLAYER_STRENGTH_EXPERIENCE_VALUE = "strengthEXPValue"
        const val KEY_PLAYER_STRENGTH_EXPERIENCE_MAX = "strengthEXPMax"
        const val KEY_PLAYER_AGILITY = "agility"
        const val KEY_PLAYER_AGILITY_EXPERIENCE_VALUE = "agilityEXPValue"
        const val KEY_PLAYER_AGILITY_EXPERIENCE_MAX = "agilityEXPMax"
        const val KEY_PLAYER_ENDURANCE = "endurance"
        const val KEY_PLAYER_ENDURANCE_EXPERIENCE_VALUE = "enduranceEXPValue"
        const val KEY_PLAYER_ENDURANCE_EXPERIENCE_MAX = "enduranceEXPMax"
        const val KEY_PLAYER_INTELLIGENCE = "intelligence"
        const val KEY_PLAYER_INTELLIGENCE_EXPERIENCE_VALUE = "intelligenceEXPValue"
        const val KEY_PLAYER_INTELLIGENCE_EXPERIENCE_MAX = "intelligenceEXPMax"
        const val KEY_PLAYER_WISDOM = "wisdom"
        const val KEY_PLAYER_WISDOM_EXPERIENCE_VALUE = "wisdomEXPValue"
        const val KEY_PLAYER_WISDOM_EXPERIENCE_MAX = "wisdomEXPMax"
        const val KEY_PLAYER_CHARISMA = "charisma"
        const val KEY_PLAYER_CHARISMA_EXPERIENCE_VALUE = "charismaEXPValue"
        const val KEY_PLAYER_CHARISMA_EXPERIENCE_MAX = "charismaEXPMax"
        const val KEY_PLAYER_ACCOUNTID = "accountID"

        //Inventory Columns
        /*  const val KEY_INVENTORY_ID = "id"
          const val KEY_ITEM_ONE = "itemOne"
          const val KEY_ITEM_TWO  = "itemTwo"
          const val KEY_ITEM_THREE = "itemThree"
          const val KEY_ITEM_FOUR = "itemFour"
          const val KEY_ITEM_FIVE = "itemFour"
          const val KEY_ITEM_SIX = "itemSix"


          private val CREATE_TABLE_INVENTORY = ("CREATE TABLE " + TABLE_INVENTORY + "("
                  + KEY_INVENTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                  + KEY_ITEM_ONE + " VARCHAR(20),"
                  + KEY_ITEM_TWO + " VARCHAR(20),"
                  + KEY_ITEM_THREE + " VARCHAR(20),"
                  + KEY_ITEM_FOUR + " VARCHAR(20),"
                  + KEY_ITEM_FIVE + " VARCHAR(20),"
                  + KEY_ITEM_SIX + " VARCHAR(20),"
                  + ");")*/


        //Create Tables
        private val CREATE_TABLE_ACCOUNT = ("CREATE TABLE " + TABLE_ACCOUNT + "("
                + KEY_ACCOUNT_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_ACCOUNT_USERNAME + " VARCHAR(20) UNIQUE,"
                + KEY_ACCOUNT_PASSWORD + " VARCHAR(20),"
                + KEY_ACCOUNT_PERSONID + " INTEGER,"
                + KEY_ACCOUNT_PLAYERID + " INTEGER"
                + ");")

        private val CREATE_TABLE_PERSON = ("CREATE TABLE " + TABLE_PERSON + "("
                + KEY_PERSON_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PERSON_FIRSTNAME + " VARCHAR(20),"
                + KEY_PERSON_LASTNAME + " VARCHAR(20),"
                + KEY_PERSON_EMAIL + " VARCHAR(40),"
                + KEY_PERSON_PHONE + " VARCHAR(15),"
                + KEY_PERSON_ACCOUNTID + " INTEGER,"
                + "FOREIGN KEY(" + KEY_PERSON_ACCOUNTID +") REFERENCES " + TABLE_ACCOUNT + "(" + KEY_ACCOUNT_ID + ")"
                + ");")

        private val CREATE_TABLE_PLAYER = ("CREATE TABLE " + TABLE_PLAYER + "("
                + KEY_PLAYER_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PLAYER_LEVEL + " INTEGER, "
                + KEY_PLAYER_LEVEL_EXPERIENCE_VALUE + " INTEGER, "
                + KEY_PLAYER_LEVEL_EXPERIENCE_MAX + " INTEGER, "
                + KEY_PLAYER_STRENGTH + " INTEGER, "
                + KEY_PLAYER_STRENGTH_EXPERIENCE_VALUE + " INTEGER, "
                + KEY_PLAYER_STRENGTH_EXPERIENCE_MAX + " INTEGER, "
                + KEY_PLAYER_AGILITY + " INTEGER,"
                + KEY_PLAYER_AGILITY_EXPERIENCE_VALUE + " INTEGER, "
                + KEY_PLAYER_AGILITY_EXPERIENCE_MAX + " INTEGER, "
                + KEY_PLAYER_ENDURANCE + " INTEGER, "
                + KEY_PLAYER_ENDURANCE_EXPERIENCE_VALUE + " INTEGER, "
                + KEY_PLAYER_ENDURANCE_EXPERIENCE_MAX + " INTEGER, "
                + KEY_PLAYER_INTELLIGENCE + " INTEGER,"
                + KEY_PLAYER_INTELLIGENCE_EXPERIENCE_VALUE + " INTEGER, "
                + KEY_PLAYER_INTELLIGENCE_EXPERIENCE_MAX + " INTEGER, "
                + KEY_PLAYER_WISDOM + " INTEGER,"
                + KEY_PLAYER_WISDOM_EXPERIENCE_VALUE + " INTEGER, "
                + KEY_PLAYER_WISDOM_EXPERIENCE_MAX + " INTEGER, "
                + KEY_PLAYER_CHARISMA + " INTEGER, "
                + KEY_PLAYER_CHARISMA_EXPERIENCE_VALUE + " INTEGER, "
                + KEY_PLAYER_CHARISMA_EXPERIENCE_MAX + " INTEGER, "
                + KEY_PLAYER_ACCOUNTID + " INTEGER, "
                + "FOREIGN KEY(" + KEY_PERSON_ACCOUNTID +") REFERENCES " + TABLE_ACCOUNT + "(" + KEY_ACCOUNT_ID + ")"
                + ");")
        private val FOREIGNKEY_ACCOUNT = (
                "ALTER TABLE " + TABLE_ACCOUNT + " "
                )
    }
}