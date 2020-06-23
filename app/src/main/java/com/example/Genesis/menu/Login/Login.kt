package com.example.Genesis.menu.Login

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Account.Setting
import com.example.Genesis.menu.Login.LoginContract.LoginInterface


class Login : AppCompatActivity(), LoginInterface {

    private lateinit var userNameValue : TextView
    private lateinit var passWordValue : TextView
    private lateinit var loginButton : Button
    private lateinit var registerButton : Button
    private lateinit var wipeButton : Button
    private lateinit var countButton : Button
    lateinit var mediaPlayer: MediaPlayer
    lateinit var loginDatabase : LoginDatabase
    lateinit var loginPresenter  : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        loginDatabase = LoginDatabase(this)
        loginPresenter = LoginPresenter(this,loginDatabase)
        mediaPlayer = MediaPlayer.create(this, R.raw.yousayrun);
        mediaPlayer.start();
        val log1 = (Math.log((Setting.maxVolume - Setting.currentVolume).toDouble()) / Math.log(Setting.maxVolume.toDouble())).toFloat()
        mediaPlayer.setVolume(log1, log1) //set volume takes two paramater

        loginButton.setOnClickListener(){
           loginPresenter.validateAccount(userNameValue.text.toString(),passWordValue.text.toString())
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

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
