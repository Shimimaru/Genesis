package com.example.Genesis.menu.Social.Friends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.Genesis.R

class FriendsList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_list)
        var recList : RecyclerView = findViewById(R.id.friendRecList)
    }
}
