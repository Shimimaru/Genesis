package com.example.Genesis.menu.Quest

import com.example.Genesis.objects.Quest
import com.example.Genesis.objects.Rewards

class QuestContract {

    interface QuestInterface{
        fun initViews()
        fun showQuest(questName : String,questDescription : String,questRewards : Rewards)
    }

    interface QuestPresenterInterface{
        fun generateQuest()
        fun showCreateMenu()
        fun questList()
    }

    interface QuestCreateInterface{
        fun initViews()
        fun showMap()
    }

    interface QuestCreatePresenterInterface{
        fun createQuest(questName : String,questDescription : String,questLevel : Int)
    }

    interface QuestDatabaseInterface{
        fun getQuest() : ArrayList<Quest>
        fun insertQuest(quest : Quest)
        fun updateTrackQuest()
        fun deleteQuest(questID : Int)
        fun getTrackedQuest() : Quest
    }

};