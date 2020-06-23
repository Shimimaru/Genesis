package com.example.Genesis.user.Account

import android.content.Context
import com.example.Genesis.menu.Register.Register
import com.example.Genesis.user.Person.Person
import com.example.Genesis.user.Player.Player

class AccountContract {
    interface AccountInterface {

    }

    interface AccountDatabaseInterface{
        fun insertAccount(person: Person, player : Player,register : Register)
    }
}