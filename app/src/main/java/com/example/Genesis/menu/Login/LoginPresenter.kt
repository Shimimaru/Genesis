package com.example.Genesis.menu.Login

import android.content.Intent
import com.example.Genesis.menu.Login.LoginContract.LoginPresenterInterface
import com.example.Genesis.menu.MainMenu.MainMenu
import com.example.Genesis.menu.Register.Register
import com.example.Genesis.user.Account.Account

class LoginPresenter(private var login : Login,var loginDB : LoginDatabase) : LoginPresenterInterface {

    override fun initAccount(accNum: Int) {
        var account : Account = Account().accountDB.getAccount(
            Account.accountNumber)
        Account.player = account.player
        Account.person = account.person
    }

    override fun validateAccount(username: String, password: String): Boolean {
        return if(loginDB.checkUserName(username)) {
            if (loginDB.checkPassWord(username, password)) {

                Account.accountNumber = loginDB.getAccountID(username)
                true
            } else {
                login.showMessage("Wrong Password")
                false
            }
        } else {
            login.showMessage("Username not Found")
            false
        }
    }

    override fun showMainMenu() {
        val i = Intent(login, MainMenu::class.java)
        login.startActivity(i)
    }

    override fun showRegister() {
        val i = Intent(login, Register::class.java)
        login.startActivity(i);
    }
}