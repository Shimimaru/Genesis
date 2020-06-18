package com.example.Genesis.menu.Social.Guild

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Quest.GuildDatabase
import com.example.Genesis.menu.Quest.QuestsCreate.QuestCreatePresenter


class GuildCreateMenu () : AppCompatActivity(), GuildContract.GuildCreateInterface{
    lateinit var guildPresenter : GuildCreatePresenter
    lateinit var guildDatabase : GuildDatabase
    lateinit var guildDescription : TextView
    lateinit var guildName : EditText
    lateinit var guildCreateButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_create_menu)
        guildDatabase =
            GuildDatabase(this)

        initViews()
        guildCreateButton.setOnClickListener(){
            guildPresenter.createGuild(guildName.text.toString(),guildDescription.text.toString())
        }


    }
    override fun initViews() {
        TODO("Not yet implemented")
    }
}