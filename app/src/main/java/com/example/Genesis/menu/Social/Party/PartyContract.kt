package com.example.Genesis.menu.Social.Party

import com.example.Genesis.objects.Party

class PartyContract {

    interface PartyInterface{
        fun initViews()
        fun showParty(partyName : String,partyDescription : String)
    }

    interface PartyPresenterInterface{
        fun generateParty()
        fun showCreateMenu()
        fun partyList()
    }
    interface PartyCreateInterface{

        fun initViews()

    }

    interface PartyCreatePresenterInterface{
        fun createParty(partyName : String, partyDescription : String)
    }

    interface PartyDatabaseInterface{
        fun getParty() : ArrayList<Party>
        fun insertParty(party : Party)
        fun updateParty()
        fun deleteParty(partyID : Int)
        fun getTrackedParty() : Party

    }
}