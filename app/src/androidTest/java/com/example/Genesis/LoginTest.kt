package com.example.Genesis

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.Genesis.menu.Login.Login
import com.example.Genesis.menu.MainMenu.MainMenu
import com.example.Genesis.menu.Register.Register
import junit.framework.AssertionFailedError
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class LoginTest {
    private lateinit var usernameToBetyped: String
    private lateinit var passwordToBetyped: String

    @get:Rule
    var intentRule: IntentsTestRule<Login> = IntentsTestRule(
        Login::class.java)

    @Before
    fun initValidString() {
        usernameToBetyped = "Username"
        passwordToBetyped = "Password Here"
    }

    @Test
    fun mainTest() {
        onView(withId(R.id.usernameTextBox)).check(matches(withText(usernameToBetyped)))
        onView(withId(R.id.passwordTextBox)).check(matches(withText(passwordToBetyped)))
        try {
            onView(withId(R.id.loginButton)).perform(click())
            intended(hasComponent(MainMenu::class.java.getName()))
        } catch (e : AssertionFailedError) {
            onView(withId(R.id.registerButton)).perform(click())
            intended(hasComponent(Register::class.java.getName()))
        }
    }
}
