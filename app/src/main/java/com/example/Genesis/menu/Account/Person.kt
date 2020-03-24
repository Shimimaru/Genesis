package com.example.Genesis.menu.Account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.Genesis.R
import com.example.Genesis.user.Account.Account

class Person : AppCompatActivity() {
    lateinit var firstName : TextView
    lateinit var lastName : TextView
    lateinit var email : TextView
    lateinit var phone : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)
        initView()
        updateView()
    }

    private fun initView(){
        firstName = findViewById(R.id.accountFirstNameValue)
        lastName = findViewById(R.id.accountLastNameValue)
        email = findViewById(R.id.accountEmailValue)
        phone = findViewById(R.id.accountPhoneValue)
    }

    private fun updateView(){
        var account : Account = Account().accountDB.getAccount(Account.accountNumber)
        firstName.text = account.person.firstName
        lastName.text = account.person.lastName
        email.text = account.person.email
        phone.text = account.person.phone
    }
}
