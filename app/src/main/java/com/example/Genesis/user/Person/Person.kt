package com.example.Genesis.user.Person

import com.example.Genesis.user.Account.Account
import java.io.Serializable

class Person(): Serializable{

    var firstName : String = ""
    var lastName:String = ""
    var email:String = ""
    var phone:String = ""

    lateinit var personPresenter : PersonPresenter
    var personDB : PersonDatabase

    init{
        personDB = PersonDatabase(this)
    }

    constructor(firstName:String,lastName:String,email:String,phone:String) : this() {
        personDB = PersonDatabase(this)
        personPresenter = PersonPresenter(this,personDB)
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.phone = phone
        personDB.updatePerson(Account.accountNumber)
    }
}