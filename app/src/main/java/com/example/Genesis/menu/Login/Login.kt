package com.example.Genesis.menu.Login

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.database.Database
import com.example.Genesis.menu.Login.LoginContract.*


class Login : AppCompatActivity(), LoginInterface {

    private lateinit var userNameValue : TextView
    private lateinit var passWordValue : TextView
    private lateinit var loginButton : Button
    private lateinit var registerButton : Button
    private lateinit var wipeButton : Button
    private lateinit var countButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        var loginDatabase : LoginDatabase = LoginDatabase(this)
        var loginPresenter  : LoginPresenter = LoginPresenter(this,loginDatabase)
        loginButton.setOnClickListener(){
            if(loginPresenter.validateAccount(userNameValue.text.toString(),passWordValue.text.toString()))
            {
                loginPresenter.showMainMenu()
            }
        }
        registerButton.setOnClickListener()
        {
            loginPresenter.showRegister()
        }
        wipeButton.setOnClickListener(){
            loginPresenter.loginDB.db.wipeDatabase(this)
        }

    }

    override fun showMessage(message: String) {
        var toast : Toast = Toast.makeText(this,message, LENGTH_SHORT)
        toast.show()
    }

    override fun initViews()
    {
        this.userNameValue = findViewById<TextView>(R.id.usernameTextBox)
        this.passWordValue = findViewById<TextView>(R.id.passwordTextBox)
        this.loginButton = findViewById<Button>(R.id.loginButton)
        this.registerButton = findViewById<Button>(R.id.registerButton)
        this.wipeButton = findViewById<Button>(R.id.wipeButton)
        this.countButton = findViewById<Button>(R.id.countButton)
    }
}
