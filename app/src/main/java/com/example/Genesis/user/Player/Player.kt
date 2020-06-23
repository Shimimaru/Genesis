package com.example.Genesis.user.Player

import com.example.Genesis.user.Player.PlayerContract.*
import java.io.Serializable

class Player() : Serializable, PlayerInterface {
    lateinit var playerPresenter : PlayerPresenter
    lateinit var playerDB : PlayerDatabase
    var playerID : Int = 0
    var level: Int = 0
    var levelExperienceValue : Int = 0
    var levelExperienceMax : Int = 0
    var strength: Int = 0
    var strengthExperienceValue : Int = 0
    var strengthExperienceMax : Int = 0
    var agility: Int = 0
    var agilityExperienceValue : Int = 0
    var agilityExperienceMax : Int = 0
    var endurance: Int = 0
    var enduranceExperienceValue : Int = 0
    var enduranceExperienceMax : Int = 0
    var intelligence: Int = 0
    var intelligenceExperienceValue : Int = 0
    var intelligenceExperienceMax : Int = 0
    var wisdom: Int = 0
    var wisdomExperienceValue : Int = 0
    var wisdomExperienceMax : Int = 0
    var charisma: Int = 0
    var charismaExperienceValue : Int = 0
    var charismaExperienceMax : Int = 0

    init {
        playerDB = PlayerDatabase(this)
        playerPresenter = PlayerPresenter(this,playerDB)
        this.level = 1
        this.levelExperienceValue = 0
        this.levelExperienceMax= 100
        this.strength = 10
        this.strengthExperienceValue = 0
        this.strengthExperienceMax = 100
        this.agility = 10
        this.agilityExperienceValue = 0
        this.agilityExperienceMax = 100
        this.endurance = 10
        this.enduranceExperienceValue = 0
        this.enduranceExperienceMax = 100
        this.intelligence = 10
        this.intelligenceExperienceValue = 0
        this.intelligenceExperienceMax = 100
        this.wisdom = 10
        this.wisdomExperienceValue = 0
        this.wisdomExperienceMax = 100
        this.charisma = 10
        this.charismaExperienceValue = 0
        this.charismaExperienceMax = 100
    }

    override fun toString(): String {
        var string = ""
        if(level != null)
            string += level.toString() + "_"
        else
            string += "null_"
        if(levelExperienceValue != null)
            string += levelExperienceValue.toString() + "_"
        else
            string += "null_"
        if(levelExperienceMax != null)
            string += levelExperienceMax.toString() + "_"
        else
            string += "null_"
        if(strength != null)
            string += strength.toString() + "_"
        else
            string += "null_"
        if(strengthExperienceValue != null)
            string += strengthExperienceValue.toString() + "_"
        else
            string += "null_"
        if(strengthExperienceMax != null)
            string += strengthExperienceMax.toString() + "_"
        else
            string += "null_"
        if(agility != null)
            string += agility.toString() + "_"
        else
            string += "null_"
        if(agilityExperienceValue != null)
            string += agilityExperienceValue.toString() + "_"
        else
            string += "null_"
        if(agilityExperienceMax != null)
            string += agilityExperienceMax.toString() + "_"
        else
            string += "null_"
        if(endurance != null)
            string += endurance.toString() + "_"
        else
            string += "null_"
        if(enduranceExperienceValue != null)
            string += enduranceExperienceValue.toString() + "_"
        else
            string += "null_"
        if(enduranceExperienceMax != null)
            string += enduranceExperienceMax.toString() + "_"
        else
            string += "null_"
        if(intelligence != null)
            string += intelligence.toString() + "_"
        else
            string += "null_"
        if(intelligenceExperienceValue != null)
            string += intelligenceExperienceValue.toString() + "_"
        else
            string += "null_"
        if(intelligenceExperienceMax != null)
            string += intelligenceExperienceMax.toString() + "_"
        else
            string += "null_"
        if(wisdom != null)
            string += wisdom.toString() + "_"
        else
            string += "null_"
        if(wisdomExperienceValue != null)
            string += wisdomExperienceValue.toString() + "_"
        else
            string += "null_"
        if(wisdomExperienceMax != null)
            string += wisdomExperienceMax.toString() + "_"
        else
            string += "null_"
        if(charisma != null)
            string += charisma.toString() + "_"
        else
            string += "null_"
        if(charismaExperienceValue != null)
            string += charismaExperienceValue.toString() + "_"
        else
            string += "null_"
        if(charismaExperienceMax != null)
            string += charismaExperienceMax.toString() + "_"
        else
            string += "null_"
        return string
    }
}