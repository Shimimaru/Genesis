package com.example.Genesis.menu.Login

class LoginContract {
    interface LoginInterface{
        fun showMessage(message : String){}
        fun initViews(){}
    }

    interface LoginPresenterInterface
    {
        fun initAccount(accNum : Int)
        fun validateAccount(username:String,password: String) : Boolean
        fun showMainMenu()
        fun showRegister()
    }

    interface LoginDatabaseInterface
    {
        fun checkUserName(username : String):Boolean
        fun checkPassWord(username : String,password : String):Boolean
        fun getAccountID(username : String):Int
    }
}