package com.example.Genesis.menu.Social.Guild.GuildMenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Social.Guild.GuildContract.GuildInterface
import com.example.Genesis.menu.Social.Guild.GuildPresenter
import com.example.Genesis.menu.Social.GuildDatabase
import java.io.Serializable
import com.example.Genesis.objects.Guild

class GuildMenu : AppCompatActivity(), GuildInterface,Serializable {

    lateinit var guildName: TextView
    lateinit var guildDescription: TextView
    lateinit var guildGenerateButton: Button
    lateinit var guildDeleteButton: Button
    lateinit var guildJoinButton: Button
    lateinit var listView: ListView
    lateinit var guildPresenter: GuildPresenter
    lateinit var guildDatabase: GuildDatabase
    lateinit var guildCreateButton: Button
    var nameList: ArrayList<String> = ArrayList<String>()
    var currentPosition = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guild_menu)
        guildDatabase =
            GuildDatabase(this)
        guildPresenter =
            GuildPresenter(
                this,
                guildDatabase
            )
        initViews()
        listView.invalidateViews()
        guildGenerateButton.setOnClickListener() {
            guildDatabase.getGeneratedGuild()
            sortNameList()
        }
        guildCreateButton.setOnClickListener() {
            guildPresenter.showCreateMenu()
            sortNameList()
        }
        guildJoinButton.setOnClickListener() {
            trackedGuild = guildList.get(currentPosition).id
            guildDatabase.updateGuild()
        }

        guildDeleteButton.setOnClickListener() {
            guildDatabase.deleteGuild(guildList.get(currentPosition).id)
            arrayAdapter.remove(guildList.get(currentPosition).name)
            sortNameList()
        }
        listView.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
            guildName.text = guildList.get(i).name
            currentPosition = i;
            guildDescription.text = guildList.get(i).description
        }
    }

    @SuppressLint("ResourceType")
    override fun initViews() {
        guildName = findViewById<TextView>(R.id.guildNameValue)
        guildDescription = findViewById<TextView>(R.id.guildDescriptionValue)
        guildGenerateButton = findViewById<Button>(R.id.guildGenerateButton)
        guildDeleteButton = findViewById<Button>(R.id.guildDeleteButton)
        guildCreateButton = findViewById<Button>(R.id.guildCreateButton)
        guildJoinButton = findViewById<Button>(R.id.guildJoinButton)
        listView = findViewById<ListView>(R.id.listView)
        guildList = guildDatabase.getGuild()
        for (i in 0 until guildList.size) {
            nameList.add(guildList.get(i).name)
            print(guildList.get(i).name)
        }
        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList)
        listView.adapter = arrayAdapter
    }

    override fun showGuild(guildName: String, guildDescription: String) {
        TODO("Not yet implemented")
    }
    fun sortNameList(){
        nameList.clear()
        guildList = guildDatabase.getGuild()
        for(i in 0 until guildList.size)
        {
            nameList.add(guildList.get(i).name)
            print(guildList.get(i).name)
        }
        arrayAdapter.notifyDataSetChanged()
    }
    companion object{
        var trackedGuild : Int = -1;
        lateinit var arrayAdapter : ArrayAdapter<String>
        lateinit var guildList : ArrayList<Guild>
    }



}

