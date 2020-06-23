package com.example.Genesis.menu.Login

class LoginContract {
    interface LoginInterface{
        fun showMessage(message : String){}
        fun initViews(){}
    }

    interface LoginPresenterInterface
    {
        fun initAccount(accNum : Int)
        fun validateAccount(username:String,password: String)
        fun showMainMenu()
        fun showRegister()
    }

    interface LoginDatabaseInterface
    {
        fun checkUserName(username : String,password: String)
        fun checkPassWord(username : String,password : String)
    }
}