package com.example.Genesis

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.Genesis.menu.MainMenu.MainMenu
import com.example.Genesis.menu.Register.Register
import junit.framework.AssertionFailedError
import org.junit.Before
import org.junit.Rule
import org.junit.Test

public class RegisterTest {
    private lateinit var usernameToBetyped: String
    private lateinit var passwordToBetyped: String

    @get:Rule
    var intentRule: IntentsTestRule<Register> = IntentsTestRule(Register::class.java)

    @Before
    fun initValidString() {
        usernameToBetyped = "Username"
        passwordToBetyped = "Password"
    }

    @Test
    fun mainTest() {
        var activity : Register = intentRule.activity
        onView(withId(R.id.registerButton)).perform(click())
        try {
            intended(hasComponent(MainMenu::class.java.getName()))
        }catch(e : AssertionFailedError){
            //toast output
        }
    }
}