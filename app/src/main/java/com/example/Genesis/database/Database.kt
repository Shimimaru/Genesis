package com.example.Genesis.database
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.Genesis.objects.Friend
import com.example.Genesis.objects.Quest
import com.example.Genesis.objects.Guild
import com.example.Genesis.objects.Party
import com.google.android.gms.maps.model.LatLng

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    init {
        Log.d("table", CREATE_TABLE_ACCOUNT)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_ACCOUNT)
        db.execSQL(CREATE_TABLE_PERSON)
        db.execSQL(CREATE_TABLE_PLAYER)
        db.execSQL(CREATE_TABLE_QUEST)
        db.execSQL(CREATE_TABLE_QUEST_GENERATED)
        db.execSQL(CREATE_TABLE_GUILD)
        db.execSQL(CREATE_TABLE_GUILD_GENERATED)
        db.execSQL(CREATE_TABLE_PARTY)
        db.execSQL(CREATE_TABLE_PARTY_GENERATED)
        db.execSQL(CREATE_TABLE_FRIEND)
        db.execSQL(CREATE_TABLE_FRIEND_GENERATED)
        db.execSQL(CREATE_TABLE_EVENT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_ACCOUNT'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_PERSON'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_PLAYER'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_QUEST'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_QUEST_GENERATED'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_GUILD'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_GUILD_GENERATED'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_PARTY'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_PARTY_GENERATED'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_FRIEND'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_FRIEND_GENERATED'")
        db.execSQL("DROP TABLE IF EXISTS '$CREATE_TABLE_EVENT'")
        onCreate(db)
    }

    fun initQuestGenerator() {
        insertGeneQuest(
            Quest(
                0,
                "Go to Auckland",
                "Go to Destination",
                1,
                LatLng(-36.848461, 174.763336)
            )
        )
        insertGeneQuest(
            Quest(
                1,
                "Go to Botany",
                "Go to Destination",
                1,
                LatLng(-36.910000, 174.917000)
            )
        )
        insertGeneQuest(
            Quest(
                2,
                "Go to Manukau",
                "Go to Destination",
                1,
                LatLng(-37.054901, 174.550003)
            )
        )
    }


    fun insertGeneQuest(quest : Quest){
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_QUEST_NAME,quest.name)
        cv.put(Database.KEY_QUEST_DESCRIPTION,quest.description)
        cv.put(Database.KEY_QUEST_LEVEL,quest.level)
        cv.put(Database.KEY_QUEST_LATITUDE, quest.latlng?.latitude)
        cv.put(Database.KEY_QUEST_LONGITUDE, quest.latlng?.longitude)
        var re : Long = Database.dbw.insert(Database.TABLE_QUEST_GENERATED,null,cv)
    }

    fun initGuildGenerator() {
        insertGeneGuild(
            Guild(
                0,
                "Guild 1",
                "For people interested in weapons"

            )
        )
        insertGeneGuild(
            Guild(
                1,
                "Guild 2",
                "For people interested in quests"

            )
        )
        insertGeneGuild(
            Guild(
                2,
                "Guild 2",
                "For people interested in stats"

            )
        )
    }
    fun insertGeneGuild(guild: Guild ){
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_GUILD_NAME,guild.name)
        cv.put(Database.KEY_GUILD_DESCRIPTION,guild.description)
        var re : Long = Database.dbw.insert(Database.TABLE_GUILD_GENERATED,null,cv)
    }



    fun initPartyGenerator() {
        insertGeneParty(
            Party(
                0,
                "Party 1",
                "For people interested in combat"

            )
        )
        insertGeneParty(
            Party(
                1,
                "Party 2",
                "For people interested in items"

            )
        )
        insertGeneParty(
            Party(
                2,
                "Party 3",
                "For people interested in stats"

            )
        )
    }
    fun insertGeneParty(party: Party ){
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_PARTY_NAME,party.name)
        cv.put(Database.KEY_PARTY_DESCRIPTION,party.description)
        var re : Long = Database.dbw.insert(Database.TABLE_PARTY_GENERATED,null,cv)
    }

    fun initFriendGenerator() {
        insertGeneFriend(
            Friend(
                0,
                "Friend 1",
                "For people interested in combat"

            )
        )
        insertGeneFriend(
            Friend(
                1,
                "Friend 2",
                "For people interested in items"

            )
        )
        insertGeneFriend(
            Friend(
                2,
                "Friend 3",
                "For people interested in stats"

            )
        )
    }
    fun insertGeneFriend(friend: Friend){
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_FRIEND_NAME,friend.name)
        cv.put(Database.KEY_FRIEND_DESCRIPTION,friend.biography)
        var re : Long = Database.dbw.insert(Database.TABLE_FRIEND_GENERATED,null,cv)
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
        const val TABLE_QUEST = "accountQuest"
        const val TABLE_QUEST_GENERATED = "questGenerated"
        const val TABLE_GUILD = "accountGuild"
        const val TABLE_GUILD_GENERATED = "guildGenerated"
        const val TABLE_PARTY = "accountParty"
        const val TABLE_PARTY_GENERATED = "partyGenerated"
        const val TABLE_FRIEND = "accountGuild"
        const val TABLE_FRIEND_GENERATED = "friendGenerated"
        const val TABLE_EVENT = "event"
        //Account Columns
        const val KEY_ACCOUNT_ID = "id"
        const val KEY_ACCOUNT_USERNAME = "username"
        const val KEY_ACCOUNT_PASSWORD = "password"
        const val KEY_ACCOUNT_PERSONID = "personID"
        const val KEY_ACCOUNT_PLAYERID = "playerID"
        const val KEY_ACCOUNT_QUEST = "quest"
        const val KEY_ACCOUNT_GUILD = "guild"
        const val KEY_ACCOUNT_FRIEND = "friend"
        const val KEY_ACCOUNT_PARTY = "party"
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
        //Quest Columns
        const val KEY_QUEST_ID = "id"
        const val KEY_QUEST_ACCOUNT_ID = "accID"
        const val KEY_QUEST_NAME = "name"
        const val KEY_QUEST_DESCRIPTION = "description"
        const val KEY_QUEST_LATITUDE = "latitude"
        const val KEY_QUEST_LONGITUDE = "longitude"
        const val KEY_QUEST_LEVEL = "level"
        //Guild Columns
        const val KEY_GUILD_ID = "id"
        const val KEY_GUILD_ACCOUNT_ID = "accID"
        const val KEY_GUILD_NAME = "name"
        const val KEY_GUILD_DESCRIPTION = "description"
        //Party Columns
        const val KEY_PARTY_ID = "id"
        const val KEY_PARTY_ACCOUNT_ID = "accID"
        const val KEY_PARTY_NAME = "name"
        const val KEY_PARTY_DESCRIPTION = "description"
        //Friend Columns
        const val KEY_FRIEND_ID = "id"
        const val KEY_FRIEND_ACCOUNT_ID = "accID"
        const val KEY_FRIEND_NAME = "name"
        const val KEY_FRIEND_DESCRIPTION = "description"
        //Event Columns
        const val KEY_EVENT_ID = "id"
        const val KEY_EVENT_NAME = "name"
        const val KEY_EVENT_DESCRIPTION = "description"
        const val KEY_EVENT_NOTES = "notes"
        const val KEY_EVENT_DATE = "date"
        const val KEY_EVENT_ACCOUNT = "accNum"
        //Create Tables
        private val CREATE_TABLE_ACCOUNT = ("CREATE TABLE " + TABLE_ACCOUNT + "("
                + KEY_ACCOUNT_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_ACCOUNT_USERNAME + " VARCHAR(20) UNIQUE,"
                + KEY_ACCOUNT_PASSWORD + " VARCHAR(20),"
                + KEY_ACCOUNT_PERSONID + " INTEGER,"
                + KEY_ACCOUNT_PLAYERID + " INTEGER,"
                + KEY_ACCOUNT_QUEST + " INTEGER"
                + KEY_ACCOUNT_GUILD + " INTEGER"
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
        private val CREATE_TABLE_QUEST = ("CREATE TABLE " + TABLE_QUEST + "("
                + KEY_QUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_QUEST_ACCOUNT_ID + " INTEGER, "
                + KEY_QUEST_NAME + " VARCHAR(40), "
                + KEY_QUEST_DESCRIPTION + " VARCHAR(300), "
                + KEY_QUEST_LATITUDE + " DOUBLE, "
                + KEY_QUEST_LONGITUDE + " DOUBLE, "
                + KEY_QUEST_LEVEL + " INT"
                + ");")
        private val CREATE_TABLE_QUEST_GENERATED = ("CREATE TABLE " + TABLE_QUEST_GENERATED + "("
                + KEY_QUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_QUEST_NAME + " VARCHAR(40), "
                + KEY_QUEST_DESCRIPTION + " VARCHAR(300), "
                + KEY_QUEST_LATITUDE + " DOUBLE, "
                + KEY_QUEST_LONGITUDE + " DOUBLE, "
                + KEY_QUEST_LEVEL + " INT"
                + ");")
        private val CREATE_TABLE_GUILD = ("CREATE TABLE " + TABLE_GUILD + "("
                + KEY_GUILD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_GUILD_ACCOUNT_ID + " INTEGER, "
                + KEY_GUILD_NAME + " VARCHAR(40), "
                + KEY_GUILD_DESCRIPTION + " VARCHAR(300), "
                + ");")

        private val CREATE_TABLE_GUILD_GENERATED = ("CREATE TABLE " + TABLE_GUILD_GENERATED + "("
                + KEY_GUILD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_GUILD_NAME + " VARCHAR(40), "
                + KEY_GUILD_DESCRIPTION + " VARCHAR(300), "

                + ");")
        private val CREATE_TABLE_PARTY= ("CREATE TABLE " + TABLE_PARTY + "("
                + KEY_PARTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_PARTY_ACCOUNT_ID + " INTEGER, "
                + KEY_PARTY_NAME + " VARCHAR(40), "
                + KEY_PARTY_DESCRIPTION + " VARCHAR(300), "
                + ");")

        private val CREATE_TABLE_PARTY_GENERATED = ("CREATE TABLE " + TABLE_PARTY_GENERATED + "("
                + KEY_PARTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_PARTY_NAME + " VARCHAR(40), "
                + KEY_PARTY_DESCRIPTION + " VARCHAR(300), "

                + ");")

        private val CREATE_TABLE_FRIEND= ("CREATE TABLE " + TABLE_FRIEND + "("
                + KEY_FRIEND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_FRIEND_ACCOUNT_ID + " INTEGER, "
                + KEY_FRIEND_NAME + " VARCHAR(40), "
                + KEY_FRIEND_DESCRIPTION + " VARCHAR(300), "
                + ");")

        private val CREATE_TABLE_FRIEND_GENERATED = ("CREATE TABLE " + TABLE_FRIEND_GENERATED + "("
                + KEY_FRIEND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_FRIEND_NAME + " VARCHAR(40), "
                + KEY_FRIEND_DESCRIPTION + " VARCHAR(300), "

                + ");")

        private val CREATE_TABLE_EVENT = ("CREATE TABLE " + TABLE_EVENT + "("
                + KEY_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_EVENT_NAME + " VARCHAR(300), "
                + KEY_EVENT_DESCRIPTION + " VARCHAR(300), "
                + KEY_EVENT_NOTES + " VARCHAR(300), "
                + KEY_EVENT_DATE + " VARCHAR(300),"
                + KEY_EVENT_ACCOUNT + " INTEGER"
                + ");")
        private val FOREIGNKEY_ACCOUNT = (
                "ALTER TABLE " + TABLE_ACCOUNT + " "
                )
    }
}