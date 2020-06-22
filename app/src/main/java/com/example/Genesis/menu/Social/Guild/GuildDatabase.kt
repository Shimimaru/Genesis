package com.example.Genesis.menu.Social

import android.content.ContentValues
import com.example.Genesis.objects.Guild
import android.database.Cursor
import com.example.Genesis.database.Database
import com.example.Genesis.user.Account.Account
import com.example.Genesis.menu.Social.Guild.GuildContract.*
import com.example.Genesis.menu.Social.Guild.GuildCreateMenu
import com.example.Genesis.menu.Social.Guild.GuildMenu.GuildMenu
import java.io.Serializable

class GuildDatabase() : GuildDatabaseInterface,Serializable {

    constructor(guild : GuildMenu) : this()

    constructor(guild : GuildCreateMenu) : this()

    override fun getGuild(): ArrayList<Guild> {
        var query : String = "SELECT * FROM " + Database.TABLE_GUILD + " WHERE " + Database.KEY_GUILD_ACCOUNT_ID+ " = " + Account.accountNumber
        var cursor : Cursor = Database.dbw.rawQuery(query,null)
        var guildList : ArrayList<Guild> = ArrayList<Guild>()
        guildList.clear()
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_GUILD_ID))
            var accountid = cursor.getInt(cursor.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_GUILD_NAME))
            var description = cursor.getString(cursor.getColumnIndex(Database.KEY_GUILD_DESCRIPTION))
            var guild : Guild = Guild(id,name,description)
            guildList.add(guild)
        }
        return guildList
    }

    fun getGeneratedGuild()
    {
        var query : String = "SELECT * FROM " + Database.TABLE_GUILD_GENERATED
        var cursor : Cursor = Database.dbw.rawQuery(query,null)
        var guildList : ArrayList<Guild> = ArrayList<Guild>()
        guildList.clear()
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_GUILD_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_GUILD_NAME))
            var description = cursor.getString(cursor.getColumnIndex(Database.KEY_GUILD_DESCRIPTION))

            var guild : Guild = Guild(id,name,description)
            guildList.add(guild)
        }
        var number = (0 until guildList.size).random()
        insertGuild(guildList.get(number))

    }

    override fun insertGuild(guild: Guild) {
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_GUILD_NAME,guild.name)
        cv.put(Database.KEY_GUILD_DESCRIPTION,guild.description)
        cv.put(Database.KEY_GUILD_ACCOUNT_ID,Account.accountNumber)
        var re : Long = Database.dbw.insert(Database.TABLE_GUILD,null,cv)
        GuildMenu.arrayAdapter.add(guild.name)
        GuildMenu.arrayAdapter.notifyDataSetChanged()
        GuildMenu.guildList.add(guild)
    }

    override fun updateGuild() {
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_ACCOUNT_GUILD,GuildMenu.trackedGuild)
        Database.dbw.update(Database.TABLE_ACCOUNT,cv,Database.KEY_ACCOUNT_ID + " = " + Account.accountNumber,null)
    }

    override fun deleteGuild(guildID: Int) {
        Database.dbw.delete(Database.TABLE_GUILD,Database.KEY_GUILD_ID + " = " + guildID, null)
    }

    override fun getTrackedGuild(): Guild {
        var queryAccount : String = "SELECT * FROM " + Database.TABLE_ACCOUNT + " WHERE " + Database.KEY_ACCOUNT_ID + " = " + Account.accountNumber
        var cursorAccount : Cursor = Database.dbw.rawQuery(queryAccount,null)
        var trackID : Int = 0
        var guildList : ArrayList<Guild> = ArrayList<Guild>()
        guildList.clear()
        while(cursorAccount.moveToNext())
        {
            trackID = cursorAccount.getInt(cursorAccount.getColumnIndex(Database.KEY_ACCOUNT_GUILD))
        }
        var queryGuild : String = "SELECT * FROM " + Database.TABLE_GUILD + " WHERE " + Database.KEY_GUILD_ID + " = " + trackID
        var cursorGuild : Cursor = Database.dbw.rawQuery(queryGuild,null)
        var guild : Guild = Guild(0,"","")
        while(cursorGuild.moveToNext())
        {
            var id = cursorGuild.getInt(cursorGuild.getColumnIndex(Database.KEY_GUILD_ID))
            var accountid = cursorGuild.getInt(cursorGuild.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursorGuild.getString(cursorGuild.getColumnIndex(Database.KEY_GUILD_NAME))
            var description = cursorGuild.getString(cursorGuild.getColumnIndex(Database.KEY_GUILD_DESCRIPTION))

            guild = Guild(id,name,description)
        }
        return guild
    }

}