package com.example.Genesis.menu.MainMenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.Genesis.R
import com.example.Genesis.menu.Map.Map
import com.example.Genesis.menu.Account.Account
import com.example.Genesis.menu.Quest.QuestMenu.QuestMenu
import com.example.Genesis.menu.Account.Setting
import com.example.Genesis.menu.Account.planner.Planner

class MainMenu : AppCompatActivity() {

    private lateinit var accountButton : Button
    private lateinit var plannerButton : Button
    private lateinit var questButton : Button
    private lateinit var settingButton : Button
    private lateinit var mapButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        accountButton = findViewById<Button>(R.id.accountButton)
        accountButton.setOnClickListener()
        {
            val i = Intent(this, Account::class.java)
            startActivity(i)
        }
        plannerButton = findViewById<Button>(R.id.plannerButton)
        plannerButton.setOnClickListener()
        {
            val i = Intent(this, Planner::class.java)
            startActivity(i)
        }
        questButton = findViewById<Button>(R.id.questButton)
        questButton.setOnClickListener()
        {
            val i = Intent(this, QuestMenu::class.java)
            startActivity(i);
        }
        settingButton = findViewById<Button>(R.id.settingButton)
        settingButton.setOnClickListener()
        {
            val i = Intent(this, Setting::class.java)
            startActivity(i);
        }
        mapButton = findViewById<Button>(R.id.questMapButton)
        mapButton.setOnClickListener()
        {
            val i = Intent(this, Map::class.java)
            startActivity(i);
        }
    }
}
