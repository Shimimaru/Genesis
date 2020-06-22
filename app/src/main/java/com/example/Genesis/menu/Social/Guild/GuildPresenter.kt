package com.example.Genesis.menu.Social.Guild

import android.content.Intent
import com.example.Genesis.menu.Account.Account
import com.example.Genesis.menu.Social.Guild.GuildMenu.GuildMenu
import com.example.Genesis.menu.Social.GuildDatabase
import com.example.Genesis.objects.Guild
import java.io.Serializable
class GuildPresenter (var guilds : GuildMenu, var guildDB : GuildDatabase) : GuildContract.GuildPresenterInterface,Serializable{
    lateinit var account : Account
    var guildList : MutableList<Guild> = mutableListOf<Guild>()

    override fun generateGuild() {

    }

    override fun showCreateMenu() {
        val i = Intent(guilds, GuildCreateMenu::class.java)
       guilds.startActivity(i)
    }

    override fun guildList() {

    }


}