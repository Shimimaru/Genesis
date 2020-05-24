package com.example.Genesis

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.Genesis.menu.Account.planner.Planner
import com.example.Genesis.menu.Login.Login
import com.example.Genesis.menu.MainMenu.MainMenu
import com.example.Genesis.menu.Register.Register
import junit.framework.AssertionFailedError
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlannerTest {
    private lateinit var usernameToBetyped: String
    private lateinit var passwordToBetyped: String

    @get:Rule
    var intentRule: IntentsTestRule<Planner> = IntentsTestRule(Planner::class.java)

    @Before
    fun initValidString() {
        usernameToBetyped = "Username"
        passwordToBetyped = "Password"
    }

    @Test
    fun mainTest() {
        onView(ViewMatchers.withId(R.id.addTab)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.addTab)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.editTab)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.deleteTab)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.viewTab)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.calendarView)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.eventText)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.eventDescription)).check(matches(isDisplayed()))
    }

}