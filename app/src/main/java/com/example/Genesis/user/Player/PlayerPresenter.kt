package com.example.Genesis.user.Player

import com.example.Genesis.objects.ExperienceTypes
import com.example.Genesis.user.Account.Account
import com.example.Genesis.user.Player.PlayerContract.PlayerPresenterInterface

class PlayerPresenter(var player : Player,var playerDB : PlayerDatabase) : PlayerPresenterInterface {

    override fun experienceUp(type: ExperienceTypes, value : Int) {
        //LEVEL,STRENGTH,AGILITY,ENDURANCE,INTELLIGENCE,WISDOM,CHARISMA
        var expCurve : Double = 1.1
        when (type.toString())
        {
            "LEVEL" ->{
                player.levelExperienceValue = player.levelExperienceValue + value
                if(player.agilityExperienceValue > player.levelExperienceMax)
                {
                    player.level++
                    player.levelExperienceValue = player.levelExperienceValue - player.levelExperienceMax
                    player.levelExperienceMax = Math.pow(player.levelExperienceMax.toDouble(),expCurve).toInt()
                }
            }
            "STRENGTH" ->{
                player.strengthExperienceValue = player.strengthExperienceValue + value
                if(player.strengthExperienceValue > player.strengthExperienceMax)
                {
                    player.strength++
                    player.strengthExperienceValue = player.strengthExperienceValue - player.strengthExperienceMax
                    player.strengthExperienceMax = Math.pow(player.strengthExperienceMax.toDouble(),expCurve).toInt()
                }
            }
            "AGILITY" ->{
                player.agilityExperienceValue = player.agilityExperienceValue + value
                if(player.agilityExperienceValue > player.agilityExperienceMax)
                {
                    player.agility++
                    player.agilityExperienceValue = player.agilityExperienceValue - player.agilityExperienceMax
                    player.agilityExperienceMax= Math.pow(player.agilityExperienceMax.toDouble(),expCurve).toInt()
                }
            }
            "ENDURANCE" ->{
                player.enduranceExperienceValue = player.enduranceExperienceValue + value
                if(player.enduranceExperienceValue > player.enduranceExperienceMax)
                {
                    player.endurance++
                    player.enduranceExperienceValue = player.enduranceExperienceValue - player.enduranceExperienceMax
                    player.enduranceExperienceMax = Math.pow(player.enduranceExperienceMax.toDouble(),expCurve).toInt()
                }
            }
            "INTELLIGENCE" ->{
                player.intelligenceExperienceValue = player.intelligenceExperienceValue + value
                if(player.intelligenceExperienceValue> player.intelligenceExperienceMax)
                {
                    player.intelligence++
                    player.intelligenceExperienceValue = player.intelligenceExperienceValue - player.intelligenceExperienceMax
                    player.intelligenceExperienceMax = Math.pow(player.intelligenceExperienceMax.toDouble(),expCurve).toInt()
                }
            }
            "WISDOM" ->{
                player.wisdomExperienceValue = player.wisdomExperienceValue + value
                if(player.wisdomExperienceValue > player.wisdomExperienceMax)
                {
                    player.wisdom++
                    player.wisdomExperienceValue = player.wisdomExperienceValue - player.wisdomExperienceMax
                    player.wisdomExperienceMax = Math.pow(player.wisdomExperienceMax.toDouble(),expCurve).toInt()
                }
            }
            "CHARISMA" ->{
                player.charismaExperienceValue = player.charismaExperienceValue + value
                if(player.charismaExperienceValue > player.charismaExperienceMax)
                {
                    player.charisma++
                    player.charismaExperienceValue = player.charismaExperienceValue - player.charismaExperienceMax
                    player.charismaExperienceMax = Math.pow(player.charismaExperienceMax.toDouble(),expCurve).toInt()
                }
            }
        }
        playerDB.updatePlayer(Account.accountNumber)
    }
}