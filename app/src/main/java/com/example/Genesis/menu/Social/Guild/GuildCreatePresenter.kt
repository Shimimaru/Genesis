package com.example.Genesis.menu.Social.Guild


import com.example.Genesis.menu.Social.GuildDatabase
import com.example.Genesis.objects.*
import com.example.Genesis.objects.items.Item
import com.example.Genesis.user.Account.Account


class GuildCreatePresenter (var guilds : GuildCreateMenu, var guildDB : GuildDatabase) : GuildContract.GuildCreatePresenterInterface {
    lateinit var account: Account
    var guildList: MutableList<Guild> = mutableListOf<Guild>()
    var list: MutableList<Item> = mutableListOf<Item>()
    var guild: Guild = Guild(0, "", "")


//override

    init {

    }
    override fun createGuild(guildName: String, guildDescription: String) {
        if(guildName != ""||guildDescription != "") {
            guild = Guild(0,guildName, guildDescription)
            guildDB.insertGuild(guild)
            guilds.showToast("Successfully added to guild");
        }
        else
            guilds.showToast("No value entered, Please enter a value");
    }
}