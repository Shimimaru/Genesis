package com.example.Genesis.menu.Social.Guild

import com.example.Genesis.objects.Guild

class GuildContract {

    interface GuildInterface{
        fun initViews()
        fun showGuild(questName : String,questDescription : String)
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
        fun createQuest(guildName : String, guildDescription : String)
    }

    interface GuildDatabaseInterface{
        fun getGuild() : ArrayList<Guild>
        fun insertGuild(guild : Guild)
        fun updateGuild()
        fun deleteGuild(questID : Int)
        fun getTrackedGuild() : Guild

    }

}