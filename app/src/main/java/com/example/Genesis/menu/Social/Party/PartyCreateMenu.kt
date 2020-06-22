package com.example.Genesis.menu.Social.Party

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Social.PartyDatabase


class PartyCreateMenu  : AppCompatActivity(), PartyContract.PartyCreateInterface {

    lateinit var partyPresenter : PartyCreatePresenter
    lateinit var partyDatabase : PartyDatabase
    lateinit var partyDescription : TextView
    lateinit var partyName : EditText
    lateinit var partyCreateButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_create_menu)
        partyDatabase =
            PartyDatabase(this)

        initViews()
        partyCreateButton.setOnClickListener(){
            partyPresenter.createParty(partyName.text.toString(),partyDescription.text.toString())
        }


    }
    override fun initViews() {
        partyName = findViewById<EditText>(R.id.nameValue)
        partyDescription = findViewById<EditText>(R.id.descriptionValue)
        partyCreateButton = findViewById<Button>(R.id.partyCreateButton)
    }

    fun showToast(s: String) {
        var toast : Toast = Toast.makeText(this,s, Toast.LENGTH_SHORT)
        toast.show()
    }

}