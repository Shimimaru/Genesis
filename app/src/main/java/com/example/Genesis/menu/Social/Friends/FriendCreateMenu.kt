package com.example.Genesis.menu.Social.Friends

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R

class FriendCreateMenu : AppCompatActivity(), FriendContract.FriendCreateInterface {
    lateinit var friendPresenter : FriendCreatePresenter
    lateinit var friendDatabase : FriendDatabase
    lateinit var friendBiography : TextView
    lateinit var friendName : EditText
    lateinit var friendCreateButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_add_menu)
        friendDatabase =
            FriendDatabase(this)

        initViews()
        friendCreateButton.setOnClickListener(){
            friendPresenter.createFriend(friendName.text.toString(),friendBiography.text.toString())
        }

    }
    override fun initViews() {
        friendName = findViewById<EditText>(R.id.nameValue)
        friendBiography = findViewById<EditText>(R.id.descriptionValue)
        friendCreateButton = findViewById<Button>(R.id.friendAddButton)
    }

    fun showToast(s: String) {
        var toast : Toast = Toast.makeText(this,s, Toast.LENGTH_SHORT)
        toast.show()
    }
}