package com.example.Genesis.menu.Social.Guild


import com.example.Genesis.objects.Guild

class GuildContract {

    interface GuildInterface{
        fun initViews()
        fun showGuild(guildName : String,guildDescription : String)
    }

    interface GuildPresenterInterface{
        fun generateGuild()
        fun showCreateMenu()
        fun guildList()
    }
    interface GuildCreateInterface{

        fun initViews()

    }

    interface GuildCreatePresenterInterface{
        fun createGuild(guildName : String, guildDescription : String)
    }

    interface GuildDatabaseInterface{
        fun getGuild() : ArrayList<Guild>
        fun insertGuild(guild : Guild)
        fun updateGuild()
        fun deleteGuild(guildID : Int)
        fun getTrackedGuild() : Guild

    }

}