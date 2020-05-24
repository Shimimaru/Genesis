package com.example.Genesis.menu.Quest.QuestMenu


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Map.MapLocation
import com.example.Genesis.menu.Quest.QuestContract.QuestInterface
import com.example.Genesis.menu.Quest.QuestDatabase
import com.example.Genesis.objects.Quest
import com.example.Genesis.objects.Rewards
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_quest_menu.*
import java.io.Serializable


class QuestMenu : AppCompatActivity(), QuestInterface,Serializable {

    lateinit var questName : TextView
    lateinit var questDescription : TextView
    lateinit var questGenerateButton : Button
    lateinit var questAbandonButton : Button
    lateinit var questTrackButton : Button
    lateinit var listView : ListView
    lateinit var questPresenter : QuestPresenter
    lateinit var questDatabase : QuestDatabase
    lateinit var questCreateButton : Button
    var nameList : ArrayList<String> = ArrayList<String>()
    var currentPosition = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_menu)
        questDatabase =
            QuestDatabase(this)
        questPresenter =
            QuestPresenter(
                this,
                questDatabase
            )
        initViews()
        listView.invalidateViews()
        questGenerateButton.setOnClickListener(){
            questDatabase.getGeneratedQuest()
            sortNameList()
        }
        questCreateButton.setOnClickListener(){
            questPresenter.showCreateMenu()
            sortNameList()
        }
        questTrackButton.setOnClickListener(){
            trackedQuest = questList.get(currentPosition).id
            questDatabase.updateTrackQuest()
        }
        questAbandonButton.setOnClickListener(){
            questDatabase.deleteQuest(questList.get(currentPosition).id)
            arrayAdapter.remove(questList.get(currentPosition).name)
            sortNameList()
        }
        listView.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
            questName.text = questList.get(i).name
            currentPosition = i;
            questDescription.text = questList.get(i).description
        }
    }

    @SuppressLint("ResourceType")
    override fun initViews() {
        questName = findViewById<TextView>(R.id.questNameValue)
        questDescription = findViewById<TextView>(R.id.questDescriptionValue)
        questGenerateButton = findViewById<Button> (R.id.questGenerateButton)
        questAbandonButton = findViewById<Button> (R.id.questAbandonButton)
        questCreateButton = findViewById<Button>(R.id.questCreateButton)
        questTrackButton = findViewById<Button>(R.id.questTrackButton)
        listView = findViewById<ListView>(R.id.listView)
        questList = questDatabase.getQuest()
        for(i in 0 until questList.size)
        {
            nameList.add(questList.get(i).name)
            print(questList.get(i).name)
        }
        arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nameList)
        listView.adapter = arrayAdapter
    }

    override fun showQuest(questName: String, questDescription: String, questRewards: Rewards) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun sortNameList(){
        nameList.clear()
        questList = questDatabase.getQuest()
        for(i in 0 until questList.size)
        {
            nameList.add(questList.get(i).name)
            print(questList.get(i).name)
        }
        arrayAdapter.notifyDataSetChanged()
    }
    companion object{
        var trackedQuest : Int = -1;
        lateinit var arrayAdapter : ArrayAdapter<String>
        lateinit var questList : ArrayList<Quest>
    }
}
