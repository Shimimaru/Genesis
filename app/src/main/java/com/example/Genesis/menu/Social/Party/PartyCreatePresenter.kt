package com.example.Genesis.menu.Social.Party

import com.example.Genesis.menu.Social.PartyDatabase
import com.example.Genesis.objects.Party
import com.example.Genesis.user.Account.Account

class PartyCreatePresenter (var parties : PartyCreateMenu, var partyDB : PartyDatabase) : PartyContract.PartyCreatePresenterInterface {
    lateinit var account: Account
    var partyList: MutableList<Party> = mutableListOf<Party>()
    var party: Party = Party(0, "", "")

//override

    init {

    }

    override fun createParty(partyName: String, partyDescription: String) {
        if(partyName != ""||partyDescription != "") {
            party = Party(0,partyName, partyDescription)
            partyDB.insertParty(party)
            parties.showToast("Successfully added to Party");
        }
        else
            parties.showToast("No value entered, Please enter a value");
    }
    }
