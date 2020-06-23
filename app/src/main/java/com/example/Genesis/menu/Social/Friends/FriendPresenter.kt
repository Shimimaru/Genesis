package com.example.Genesis.menu.Social.Friends

import android.content.Intent
import com.example.Genesis.menu.Account.Account
import com.example.Genesis.objects.Friend
import java.io.Serializable

class FriendPresenter (var friends : FriendMenu, var guildDB : FriendDatabase) : FriendContract.FriendPresenterInterface,Serializable {
    lateinit var account: Account
    var friendList: MutableList<Friend> = mutableListOf<Friend>()

    override fun generateFriend() {

    }

    override fun showCreateMenu() {
        val i = Intent(friends, FriendCreateMenu::class.java)
        friends.startActivity(i)
    }

    override fun friendList() {

    }
}
