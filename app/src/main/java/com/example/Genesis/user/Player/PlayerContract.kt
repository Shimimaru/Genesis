package com.example.Genesis.user.Player

import com.example.Genesis.objects.ExperienceTypes

class PlayerContract {

    interface PlayerInterface {

    }

    interface PlayerPresenterInterface{
        fun experienceUp(type: ExperienceTypes, value : Int)
    }

    interface PlayerDatabaseInterface{
        fun updatePlayer(accNum : Int) : Boolean
        fun getPlayer(accNum : Int) : Player
    }
}