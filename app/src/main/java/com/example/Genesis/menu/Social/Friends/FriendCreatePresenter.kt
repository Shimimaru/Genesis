package com.example.Genesis.menu.Social.Friends

import com.example.Genesis.menu.Account.Account
import com.example.Genesis.menu.Account.Inventory.model.Item
import com.example.Genesis.objects.Friend

class FriendCreatePresenter (var friends : FriendCreateMenu, var friendDB : FriendDatabase) : FriendContract.FriendCreatePresenterInterface {
    lateinit var account: Account
    var friendList: MutableList<Friend> = mutableListOf<Friend>()
    var list: MutableList<Item> = mutableListOf<Item>()
    var friend: Friend = Friend(0, "", "")


//override

    init {

    }

    override fun createFriend(friendName: String, friendBiography: String) {
        if (friendName != "" || friendBiography != "") {
            friend = Friend(0, friendName, friendBiography)
            friendDB.insertFriend(friend)
            friends.showToast("Successfully added to guild");
        } else
            friends.showToast("No value entered, Please enter a value");
    }
}