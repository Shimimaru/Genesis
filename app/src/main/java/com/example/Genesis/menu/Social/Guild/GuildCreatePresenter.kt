package com.example.Genesis.menu.Social.Guild


import com.example.Genesis.menu.Quest.GuildDatabase
import com.example.Genesis.objects.*
import com.example.Genesis.objects.items.Item
import com.example.Genesis.user.Account.Account


class GuildCreatePresenter (var guilds : GuildCreateMenu, var guildDB : GuildDatabase) : GuildContract.GuildCreatePresenterInterface {
    lateinit var account: Account
    var questList: MutableList<Quest> = mutableListOf<Quest>()
    var list: MutableList<Item> = mutableListOf<Item>()
    var guild: Guild = Guild(0, "", "")


//override
    fun createGuild(questName: String, questDescription: String, questLevel: Int) {

    }
    init {

    }

    override fun createQuest(guildName: String, guildDescription: String) {
        TODO("Not yet implemented")
    }
}