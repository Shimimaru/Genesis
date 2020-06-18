package com.example.Genesis.menu.Account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.Genesis.R
import com.example.Genesis.menu.Account.Inventory.view.InventoryMainActivity
import com.example.Genesis.menu.Account.planner.Planner

class Account : AppCompatActivity() {

    lateinit var playerButton : Button
    lateinit var personButton : Button
    lateinit var plannerButton : Button
    lateinit var settingButton : Button
    lateinit var inventoryButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        initView()
        playerButton.setOnClickListener(){
            val i = Intent(this, Status::class.java)
            startActivity(i)
        }
        personButton.setOnClickListener(){
            val i = Intent(this, Person::class.java)
            startActivity(i)
        }
        plannerButton.setOnClickListener(){
            val i = Intent(this, Planner::class.java)
            startActivity(i)
        }
        settingButton.setOnClickListener(){
            val i = Intent(this, Setting::class.java)
            startActivity(i)
        }

        inventoryButton.setOnClickListener(){
            val i = Intent(this, InventoryMainActivity:: class.java)
            startActivity(i)
        }


    }

    private fun initView(){
        playerButton = findViewById(R.id.accountPlayerButton)
        personButton = findViewById(R.id.accountPersonButton)
        plannerButton = findViewById(R.id.accountPlannerButton)
        settingButton = findViewById(R.id.accountSettingButton)
        inventoryButton = findViewById(R.id.InventoryButton)

    }
}
