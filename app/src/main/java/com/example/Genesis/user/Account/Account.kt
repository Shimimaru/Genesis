package com.example.Genesis.user.Account

import android.content.Context
import com.example.Genesis.objects.Quest
import com.example.Genesis.user.Person.Person
import com.example.Genesis.user.Player.Player
import java.io.Serializable

class Account () : Serializable{
    var accNum : Int = 0
    var username : String = ""
    var password : String = ""
    var questList : MutableList<Quest> = mutableListOf<Quest>()
    lateinit var person : Person
    lateinit var player : Player
    lateinit var accountDB : AccountDatabase

    init{
        accountDB = AccountDatabase(this)
        person = Person()
        player = Player()
    }

    constructor(context : Context,username: String,password : String,firstName : String,lastName : String,email : String,phone : String) : this()
    {
        accountDB = AccountDatabase(this)
        this.username = username
        this.password = password
        person = Person(firstName,lastName,email,phone)
        player = Player()
        accountDB.insertAccount(person,player)
        player.playerDB.updatePlayer(accNum)
        person.personDB.updatePerson(accNum)
    }

    companion object
    {
        var accountNumber : Int = 0;
        var person : Person =
            Person()
        var player : Player =
            Player()
    }
}