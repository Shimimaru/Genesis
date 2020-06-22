package com.example.Genesis.menu.Social.Party

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Social.PartyDatabase
import com.example.Genesis.objects.Party
import java.io.Serializable

class PartyMenu : AppCompatActivity(), PartyContract.PartyInterface,Serializable {

    lateinit var partyName: TextView
    lateinit var partyDescription: TextView
    lateinit var partyGenerateButton: Button
    lateinit var partyDeleteButton: Button
    lateinit var partyJoinButton: Button
    lateinit var listView: ListView
    lateinit var partyPresenter: PartyPresenter
    lateinit var partyDatabase: PartyDatabase
    lateinit var partyCreateButton: Button
    var nameList: ArrayList<String> = ArrayList<String>()
    var currentPosition = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_menu)
        partyDatabase =
            PartyDatabase(this)
        partyPresenter =
            PartyPresenter(
                this,
                partyDatabase
            )
            initViews()
            listView.invalidateViews()
            partyGenerateButton.setOnClickListener() {
                partyDatabase.getGeneratedParty()
                sortNameList()
            }
            partyCreateButton.setOnClickListener() {
                partyPresenter.showCreateMenu()
                sortNameList()
            }
            partyJoinButton.setOnClickListener() {
                trackedParty = partyList.get(currentPosition).id
                partyDatabase.updateParty()
            }

            partyDeleteButton.setOnClickListener() {
                partyDatabase.deleteParty(partyList.get(currentPosition).id)
                arrayAdapter.remove(partyList.get(currentPosition).name)
                sortNameList()
            }
            listView.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, i, l ->
                    partyName.text = partyList.get(i).name
                    currentPosition = i;
                    partyDescription.text = partyList.get(i).description
                }
        }
    @SuppressLint("ResourceType")
    override fun initViews() {
        partyName = findViewById<TextView>(R.id.partyNameValue)
        partyDescription = findViewById<TextView>(R.id.partyDescriptionValue)
        partyGenerateButton = findViewById<Button>(R.id.partyGenerateButton)
        partyDeleteButton = findViewById<Button>(R.id.partyDeleteButton)
        partyCreateButton = findViewById<Button>(R.id.partyCreateButton)
        partyJoinButton = findViewById<Button>(R.id.partyJoinButton)
        listView = findViewById<ListView>(R.id.listView)
        partyList = partyDatabase.getParty()
        for (i in 0 until partyList.size) {
            nameList.add(partyList.get(i).name)
            print(partyList.get(i).name)
        }
        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList)
        listView.adapter = arrayAdapter
    }
    override fun showParty(partyName: String, partyDescription: String) {
        TODO("Not yet implemented")
    }
    fun sortNameList() {
            nameList.clear()
            partyList = partyDatabase.getParty()
            for (i in 0 until partyList.size) {
                nameList.add(partyList.get(i).name)
                print(partyList.get(i).name)
            }
            arrayAdapter.notifyDataSetChanged()
        }
        companion object {
            var trackedParty: Int = -1;
            lateinit var arrayAdapter: ArrayAdapter<String>
            lateinit var partyList: ArrayList<Party>
        }





}

