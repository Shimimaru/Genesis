package com.example.Genesis.menu.Quest

import com.example.Genesis.menu.Social.Guild.GuildContract.GuildDatabaseInterface
import com.example.Genesis.menu.Social.Guild.GuildMenu
import com.example.Genesis.menu.Social.Guild.GuildCreateMenu
import com.example.Genesis.objects.Guild
import java.io.Serializable

class GuildDatabase() : GuildDatabaseInterface,Serializable {

    constructor(guild : GuildMenu) : this() {

    }

    constructor(guild : GuildCreateMenu) : this() {

    }

    fun getGeneratedGuild()
    {


    }

    override fun getGuild(): ArrayList<Guild> {
        TODO("Not yet implemented")
    }

    override fun insertGuild(guild: Guild) {
        TODO("Not yet implemented")
    }

    override fun updateGuild() {
        TODO("Not yet implemented")
    }

    override fun deleteGuild(guildID: Int) {
        TODO("Not yet implemented")
    }

    override fun getTrackedGuild(): Guild {
        TODO("Not yet implemented")
    }

}