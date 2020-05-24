package com.example.Genesis.menu.Quest.QuestsCreate

import com.example.Genesis.menu.Quest.QuestContract
import com.example.Genesis.menu.Quest.QuestDatabase
import com.example.Genesis.menu.Quest.QuestMap.QuestMapPresenter
import com.example.Genesis.menu.Quest.QuestMenu.QuestMenu
import com.example.Genesis.objects.Experience
import com.example.Genesis.objects.ExperienceTypes
import com.example.Genesis.objects.Quest
import com.example.Genesis.objects.Rewards
import com.example.Genesis.objects.items.Item
import com.example.Genesis.user.Account.Account
import com.google.android.gms.maps.model.LatLng

class QuestCreatePresenter(var quests : QuestCreateMenu, var questDB : QuestDatabase) : QuestContract.QuestCreatePresenterInterface {
    lateinit var account : Account
    var questList : MutableList<Quest> = mutableListOf<Quest>()
    var list : MutableList<Item> = mutableListOf<Item>()
    var experience: Experience = Experience(ExperienceTypes.NONE,0)
    var reward = Rewards(list,experience)
    var quest : Quest = Quest(0,"","",0, LatLng(0.0,0.0))
    var item : Item = Item("Weapon")

    init{

    }

    override fun createQuest(questName: String, questDescription: String, questLevel: Int) {
        if(QuestMapPresenter.latLngValue != null||questName != ""||questDescription != ""||questLevel != null) {
            quest = Quest(0,questName, questDescription, questLevel, QuestMapPresenter.latLngValue)
            questDB.insertQuest(quest)
            quests.showToast("Successfully Added");
        }
        else
            quests.showToast("You have not entered a Value");
    }
}