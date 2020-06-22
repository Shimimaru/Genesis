package com.example.Genesis.menu.Social.Guild

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Social.GuildDatabase

class GuildCreateMenu  : AppCompatActivity(), GuildContract.GuildCreateInterface {
    lateinit var guildPresenter : GuildCreatePresenter
    lateinit var guildDatabase : GuildDatabase
    lateinit var guildDescription : TextView
    lateinit var guildName : EditText
    lateinit var guildCreateButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guild_create_menu)
        guildDatabase =
            GuildDatabase(this)

        initViews()
        guildCreateButton.setOnClickListener(){
            guildPresenter.createGuild(guildName.text.toString(),guildDescription.text.toString())
        }


    }
    override fun initViews() {
        guildName = findViewById<EditText>(R.id.nameValue)
        guildDescription = findViewById<EditText>(R.id.descriptionValue)
        guildCreateButton = findViewById<Button>(R.id.guildCreateButton)
    }

        fun showToast(s: String) {
        var toast : Toast = Toast.makeText(this,s, Toast.LENGTH_SHORT)
        toast.show()
    }
}

