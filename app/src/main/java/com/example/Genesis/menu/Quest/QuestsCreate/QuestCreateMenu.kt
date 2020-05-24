package com.example.Genesis.menu.Quest.QuestsCreate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Quest.QuestContract
import com.example.Genesis.menu.Quest.QuestDatabase
import com.example.Genesis.menu.Quest.QuestMap.QuestMap


class QuestCreateMenu() : AppCompatActivity(), QuestContract.QuestCreateInterface {

    lateinit var questPresenter : QuestCreatePresenter
    lateinit var questDatabase : QuestDatabase
    lateinit var questDescription : TextView
    lateinit var questName : EditText
    lateinit var questLevel : EditText
    lateinit var questMapButton : Button
    lateinit var questCreateButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_create_menu)
        questDatabase =
            QuestDatabase(this)
        questPresenter =
            QuestCreatePresenter(
                this,
                questDatabase
            )
        initViews()
        questCreateButton.setOnClickListener(){
            questPresenter.createQuest(questName.text.toString(),questDescription.text.toString(),Integer.valueOf(questLevel.text.toString()))
        }

        questMapButton.setOnClickListener(){
            showMap()
        }
    }

    override fun initViews(){
        questName = findViewById<EditText>(R.id.nameValue)
        questDescription = findViewById<EditText>(R.id.descriptionValue)
        questLevel = findViewById<EditText>(R.id.levelValue)
        questMapButton = findViewById<Button> (R.id.questMapButton)
        questCreateButton = findViewById<Button>(R.id.questCreateButton)
    }

    override fun showMap() {
        val i = Intent(this, QuestMap::class.java)
        this.startActivity(i)
    }

    fun showToast(string : String){
        var toast : Toast = Toast.makeText(this,string, Toast.LENGTH_SHORT)
        toast.show()
    }
}
