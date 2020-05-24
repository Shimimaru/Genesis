package com.example.Genesis.menu.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.Genesis.R
import com.example.Genesis.menu.Login.LoginDatabase
import com.example.Genesis.menu.MainMenu.MainMenu
import com.example.Genesis.user.Account.Account

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)
        var usernameTextBox = findViewById(R.id.usernameTextBox) as EditText
        var passwordTextBox = findViewById(R.id.passwordTextBox) as EditText
        var firstNameTextBox = findViewById(R.id.firstNameTextBox) as EditText
        var lastNameTextBox = findViewById(R.id.lastNameTextBox) as EditText
        var emailTextBox = findViewById(R.id.emailTextBox) as EditText
        var phoneTextBox = findViewById(R.id.phoneTextBox) as EditText
        var registerButton = findViewById(R.id.registerButton) as Button
        registerButton.setOnClickListener()
        {
            if(!LoginDatabase(this).checkUserName(usernameTextBox.text.toString())){
                var account = Account(
                    this
                    , usernameTextBox.text.toString()
                    , passwordTextBox.text.toString()
                    , firstNameTextBox.text.toString()
                    , lastNameTextBox.text.toString()
                    , emailTextBox.text.toString()
                    , phoneTextBox.text.toString()
                )
                val i = Intent(this, MainMenu::class.java)
                startActivity(i)
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Username is Taken", Toast.LENGTH_SHORT).show();
            }



        }
    }
}
