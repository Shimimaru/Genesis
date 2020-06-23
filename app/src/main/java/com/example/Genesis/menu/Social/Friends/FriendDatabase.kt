package com.example.Genesis.menu.Social.Friends
import android.content.ContentValues
import android.database.Cursor
import com.example.Genesis.database.Database
import com.example.Genesis.objects.Friend
import com.example.Genesis.user.Account.Account
import java.io.Serializable
class FriendDatabase () : FriendContract.FriendDatabaseInterface,Serializable{
    constructor(friend : FriendMenu) : this()

    constructor(friend : FriendCreateMenu) : this()

    override fun getFriend(): ArrayList<Friend> {
        var query : String = "SELECT * FROM " + Database.TABLE_FRIEND + " WHERE " + Database.KEY_FRIEND_ACCOUNT_ID+ " = " + Account.accountNumber
        var cursor : Cursor = Database.dbw.rawQuery(query,null)
        var friendList : ArrayList<Friend> = ArrayList<Friend>()
        friendList.clear()
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_FRIEND_ID))
            var accountid = cursor.getInt(cursor.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_FRIEND_NAME))
            var description = cursor.getString(cursor.getColumnIndex(Database.KEY_FRIEND_DESCRIPTION))
            var friend : Friend = Friend(id,name,description)
            friendList.add(friend)
        }
        return friendList
    }
    fun getGeneratedFriend()
    {
        var query : String = "SELECT * FROM " + Database.TABLE_FRIEND_GENERATED
        var cursor : Cursor = Database.dbw.rawQuery(query,null)
        var friendList : ArrayList<Friend> = ArrayList<Friend>()
        friendList.clear()
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(cursor.getColumnIndex(Database.KEY_FRIEND_ID))
            var name = cursor.getString(cursor.getColumnIndex(Database.KEY_FRIEND_NAME))
            var description = cursor.getString(cursor.getColumnIndex(Database.KEY_FRIEND_DESCRIPTION))

            var friend : Friend = Friend(id,name,description)
            friendList.add(friend)
        }
        var number = (0 until friendList.size).random()
        insertFriend(friendList.get(number))

    }
    override fun insertFriend(friend: Friend) {
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_FRIEND_NAME,friend.name)
        cv.put(Database.KEY_FRIEND_DESCRIPTION,friend.biography)
        cv.put(Database.KEY_FRIEND_ACCOUNT_ID,Account.accountNumber)
        var re : Long = Database.dbw.insert(Database.TABLE_FRIEND,null,cv)
        FriendMenu.arrayAdapter.add(friend.name)
        FriendMenu.arrayAdapter.notifyDataSetChanged()
        FriendMenu.friendList.add(friend)
    }

    override fun updateFriend() {
        var cv : ContentValues = ContentValues()
        cv.put(Database.KEY_ACCOUNT_FRIEND,FriendMenu.trackedFriend)
        Database.dbw.update(Database.TABLE_ACCOUNT,cv,Database.KEY_ACCOUNT_ID + " = " + Account.accountNumber,null)
    }

    override fun deleteFriend(friendID: Int) {
        Database.dbw.delete(Database.TABLE_FRIEND,Database.KEY_FRIEND_ID + " = " + friendID, null)
    }

    override fun getTrackedFriend(): Friend {
        var queryAccount : String = "SELECT * FROM " + Database.TABLE_ACCOUNT + " WHERE " + Database.KEY_ACCOUNT_ID + " = " + Account.accountNumber
        var cursorAccount : Cursor = Database.dbw.rawQuery(queryAccount,null)
        var trackID : Int = 0
        var friendList : ArrayList<Friend> = ArrayList<Friend>()
        friendList.clear()
        while(cursorAccount.moveToNext())
        {
            trackID = cursorAccount.getInt(cursorAccount.getColumnIndex(Database.KEY_ACCOUNT_FRIEND))
        }
        var queryFriend : String = "SELECT * FROM " + Database.TABLE_FRIEND + " WHERE " + Database.KEY_FRIEND_ID + " = " + trackID
        var cursorFriend : Cursor = Database.dbw.rawQuery(queryFriend,null)
        var friend : Friend = Friend(0,"","")
        while(cursorFriend.moveToNext())
        {
            var id = cursorFriend.getInt(cursorFriend.getColumnIndex(Database.KEY_FRIEND_ID))
            var accountid = cursorFriend.getInt(cursorFriend.getColumnIndex(Database.KEY_ACCOUNT_ID))
            var name = cursorFriend.getString(cursorFriend.getColumnIndex(Database.KEY_FRIEND_NAME))
            var description = cursorFriend.getString(cursorFriend.getColumnIndex(Database.KEY_FRIEND_DESCRIPTION))

            friend = Friend(id,name,description)
        }
        return friend
    }
}