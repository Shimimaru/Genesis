package com.example.Genesis.menu.Account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.Genesis.R
import com.example.Genesis.user.Account.Account

class Status : AppCompatActivity() {

    lateinit var account : Account
    lateinit var nameValue : TextView
    lateinit var levelValue : TextView
    lateinit var levelExpValue : TextView
    lateinit var strengthValue : TextView
    lateinit var strengthExpValue : TextView
    lateinit var agilityValue : TextView
    lateinit var agilityExpValue : TextView
    lateinit var enduranceValue : TextView
    lateinit var enduranceExpValue : TextView
    lateinit var intelligenceValue : TextView
    lateinit var intelligenceExpValue : TextView
    lateinit var wisdomValue : TextView
    lateinit var wisdomExpValue : TextView
    lateinit var charismaValue : TextView
    lateinit var charismaExpValue : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_screen)
        account = Account().accountDB.getAccount(Account.accountNumber)
        initViews()
        updateView()
    }

    private fun initViews(){
        nameValue = findViewById<TextView>(R.id.eventNameValue)
        levelValue = findViewById<TextView>(R.id.levelValue)
        levelExpValue = findViewById<TextView>(R.id.experienceValue)
        strengthValue = findViewById<TextView>(R.id.strengthValue)
        strengthExpValue = findViewById<TextView>(R.id.strengthExperienceValue)
        agilityValue = findViewById<TextView>(R.id.agilityValue)
        agilityExpValue = findViewById<TextView>(R.id.agilityExperienceValue)
        enduranceValue = findViewById<TextView>(R.id.enduranceValue)
        enduranceExpValue = findViewById<TextView>(R.id.enduranceExperienceValue)
        intelligenceValue = findViewById<TextView>(R.id.intelligenceValue)
        intelligenceExpValue = findViewById<TextView>(R.id.intelligenceExperienceValue)
        wisdomValue = findViewById<TextView>(R.id.wisdomValue)
        wisdomExpValue = findViewById<TextView>(R.id.wisdomExperienceValue)
        charismaValue = findViewById<TextView>(R.id.charismaValue)
        charismaExpValue = findViewById<TextView>(R.id.charismaExperienceValue)
    }

    private fun updateView(){
        nameValue.text = account.username.toString()
        levelValue.text = account.player.level.toString()
        levelExpValue.text = account.player.levelExperienceValue.toString() + "/" + account.player.levelExperienceMax.toString()
        strengthValue.text = account.player.strength.toString()
        strengthExpValue.text = account.player.strengthExperienceValue.toString() + "/" + account.player.strengthExperienceMax.toString()
        agilityValue.text = account.player.agility.toString()
        agilityExpValue.text = account.player.agilityExperienceValue.toString() + "/" + account.player.agilityExperienceMax.toString()
        enduranceValue.text = account.player.endurance.toString()
        enduranceExpValue.text = account.player.enduranceExperienceValue.toString() + "/" + account.player.enduranceExperienceMax.toString()
        intelligenceValue.text = account.player.intelligence.toString()
        intelligenceExpValue.text = account.player.intelligenceExperienceValue.toString() + "/" + account.player.intelligenceExperienceMax.toString()
        wisdomValue.text = account.player.wisdom.toString()
        wisdomExpValue.text = account.player.wisdomExperienceValue.toString() + "/" + account.player.wisdomExperienceMax.toString()
        charismaValue.text = account.player.charisma.toString()
        charismaExpValue.text = account.player.charismaExperienceValue.toString() + "/" + account.player.charismaExperienceMax.toString()
    }
}
