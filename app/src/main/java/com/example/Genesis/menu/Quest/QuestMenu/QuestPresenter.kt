package com.example.Genesis.menu.Quest.QuestMenu

import android.content.Intent
import com.example.Genesis.menu.Quest.QuestContract.QuestPresenterInterface
import com.example.Genesis.menu.Quest.QuestsCreate.QuestCreateMenu
import com.example.Genesis.menu.Quest.QuestDatabase
import com.example.Genesis.objects.Experience
import com.example.Genesis.objects.ExperienceTypes
import com.example.Genesis.objects.Quest
import com.example.Genesis.objects.Rewards
import com.example.Genesis.objects.items.Item
import com.example.Genesis.user.Account.Account
import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

class QuestPresenter(var quests : QuestMenu, var questDB : QuestDatabase) : QuestPresenterInterface,Serializable{
    lateinit var account : Account
    var questList : MutableList<Quest> = mutableListOf<Quest>()
    var list : MutableList<Item> = mutableListOf<Item>()
    var experience: Experience = Experience(ExperienceTypes.NONE,0)
    var reward = Rewards(list,experience)
    var quest : Quest = Quest(0,"","",0, LatLng(0.0,0.0))
    var item : Item = Item("Weapon")

    override fun generateQuest() {

    }

    override fun showCreateMenu() {
        val i = Intent(quests, QuestCreateMenu::class.java)
        quests.startActivity(i)
    }

    override fun questList() {

    }
}