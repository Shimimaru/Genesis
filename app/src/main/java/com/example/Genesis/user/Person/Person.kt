package com.example.Genesis.user.Person

import com.example.Genesis.user.Account.Account
import java.io.Serializable

class Person(): Serializable{
    var personid : Int = 0
    var firstName : String = ""
    var lastName:String = ""
    var email:String = ""
    var phone:String = ""

    lateinit var personPresenter : PersonPresenter
    var personDB : PersonDatabase

    init{
        personDB = PersonDatabase(this)
    }

    override fun toString(): String {
        var string = ""
        if(firstName != null)
            string += firstName + "_"
        else
            string += "null_"
        if(lastName != null)
            string += lastName + "_"
        else
            string += "null_"
        if(email != null)
            string += email + "_"
        else
            string += "null_"
        if(phone != null)
            string += phone + "_"
        else
            string += "null_"
        return string
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