package com.example.Genesis.menu.Social.Party

import android.content.Intent
import com.example.Genesis.menu.Social.PartyDatabase
import com.example.Genesis.objects.Party
import com.example.Genesis.user.Account.Account
import java.io.Serializable

class PartyPresenter (var parties : PartyMenu, var partyDB : PartyDatabase) : PartyContract.PartyPresenterInterface,Serializable{
    lateinit var account : Account
    var partyList : MutableList<Party> = mutableListOf<Party>()

    override fun generateParty() {

    }

    override fun showCreateMenu() {
        val i = Intent(parties, PartyCreateMenu::class.java)
        parties.startActivity(i)
    }

    override fun partyList() {

    }


}