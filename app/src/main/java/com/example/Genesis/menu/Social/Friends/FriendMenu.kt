package com.example.Genesis.menu.Social.Friends

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.objects.Friend


import java.io.Serializable
class FriendMenu : AppCompatActivity(), FriendContract.FriendInterface,Serializable{
    lateinit var friendName: TextView
    lateinit var friendBio: TextView
    lateinit var friendGenerateButton: Button
    lateinit var friendDeleteButton: Button
    lateinit var friendAddButton: Button
    lateinit var listView: ListView
    lateinit var friendPresenter: FriendPresenter
    lateinit var friendDatabase:FriendDatabase
    lateinit var friendCreateButton: Button
    var nameList: ArrayList<String> = ArrayList<String>()
    var currentPosition = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_menu)
        friendDatabase =
            FriendDatabase(this)
        friendPresenter =
            FriendPresenter(
                this,
                friendDatabase
            )
        initViews()
        listView.invalidateViews()
        friendGenerateButton.setOnClickListener() {
            friendDatabase.getGeneratedFriend()
            sortNameList()
        }
        friendCreateButton.setOnClickListener() {
            friendPresenter.showCreateMenu()
            sortNameList()
        }
        friendAddButton.setOnClickListener() {
            trackedFriend = friendList.get(currentPosition).id
            friendDatabase.updateFriend()
        }

        friendDeleteButton.setOnClickListener() {
            friendDatabase.deleteFriend(friendList.get(currentPosition).id)
            arrayAdapter.remove(friendList.get(currentPosition).name)
            sortNameList()
        }
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            friendName.text = friendList.get(i).name
            currentPosition = i;
            friendBio.text = friendList.get(i).biography
        }
    }
    @SuppressLint("ResourceType")
    override fun initViews() {
        friendName = findViewById<TextView>(R.id.friendNameValue)
        friendBio = findViewById<TextView>(R.id.friendDescriptionValue)
        friendGenerateButton = findViewById<Button>(R.id.friendGenerateButton)
        friendDeleteButton = findViewById<Button>(R.id.friendDeleteButton)
        friendCreateButton = findViewById<Button>(R.id.friendCreateButton)
        friendAddButton = findViewById<Button>(R.id.friendJoinButton)
        listView = findViewById<ListView>(R.id.listView)
        friendList = friendDatabase.getFriend()
        for (i in 0 until friendList.size) {
            nameList.add(friendList.get(i).name)
            print(friendList.get(i).name)
        }
        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList)
        listView.adapter = arrayAdapter
    }

    override fun showFriend(friendName: String, friendBiography: String) {
        TODO("Not yet implemented")
    }
    fun sortNameList(){
        nameList.clear()
        friendList = friendDatabase.getFriend()
        for(i in 0 until friendList.size)
        {
            nameList.add(friendList.get(i).name)
            print(friendList.get(i).name)
        }
        arrayAdapter.notifyDataSetChanged()
    }
    companion object{
        var trackedFriend : Int = -1;
        lateinit var arrayAdapter : ArrayAdapter<String>
        lateinit var friendList : ArrayList<Friend>
    }






}