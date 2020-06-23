package com.example.Genesis.menu.Social

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Social.Friends.FriendMenu
import com.example.Genesis.menu.Social.Guild.GuildMenu.GuildMenu
import com.example.Genesis.menu.Social.Party.PartyMenu


class SocialMenu : AppCompatActivity() {
    private lateinit var guildButton: Button
    private lateinit var partyButton: Button
    private lateinit var friendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_menu)
        initView()
        guildButton.setOnClickListener(){
            val i = Intent(this, GuildMenu::class.java)
            startActivity(i)
        }
        partyButton.setOnClickListener(){
            val i = Intent(this, PartyMenu::class.java)
            startActivity(i)
        }

        friendButton.setOnClickListener(){
            val i = Intent(this, FriendMenu::class.java)
            startActivity(i)
        }

    }
    private fun initView(){
        guildButton = findViewById(R.id.guildButton)

    }
}