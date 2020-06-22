package com.example.Genesis.menu.Social.Friends

import com.example.Genesis.objects.Friend

class FriendContract {
    interface FriendInterface {
        fun initViews()
        fun showFriend(friendName: String, friendBiography: String)
    }

    interface FriendPresenterInterface {
        fun generateFriend()
        fun showCreateMenu()
        fun friendList()
    }

    interface FriendCreateInterface {

        fun initViews()

    }

    interface FriendCreatePresenterInterface {
        fun createFriend(friendName: String, friendBiography: String)
    }

    interface FriendDatabaseInterface {
        fun getFriend(): ArrayList<Friend>
        fun insertFriend(friend: Friend)
        fun updateFriend()
        fun deleteFriend(friendID: Int)
        fun getTrackedFriend(): Friend

    }
}