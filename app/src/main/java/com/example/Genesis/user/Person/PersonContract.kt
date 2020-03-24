package com.example.Genesis.user.Person

class PersonContract {

    interface PersonInterface{

    }

    interface PersonPresenterInterface{

    }

    interface PersonDatabaseInterface{
        fun updatePerson(accNum : Int) : Boolean
        fun getPerson(accNum : Int) : Person
    }
}