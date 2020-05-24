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
import com.example.Genesis.menu.Quest.QuestMap.QuestMap
import com.example.Genesis.menu.Quest.QuestsCreate.QuestCreateMenu
import com.example.Genesis.menu.Register.Register
import junit.framework.AssertionFailedError
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.properties.Delegates


class QuestCreateTest {
    private lateinit var nameToBetyped: String
    private lateinit var descriptionToBetyped: String
    private var levelToBetyped by Delegates.notNull<Int>()

    @get:Rule
    var intentRule: IntentsTestRule<QuestCreateMenu> = IntentsTestRule(QuestCreateMenu::class.java)

    @Before
    fun initValidString() {
        nameToBetyped = "QuestName"
        descriptionToBetyped = "Description"

    }
    @Before
    fun initValidInt(){
        levelToBetyped = 1
    }

    @Test
    fun mainTest() {
        onView(withId(R.id.nameValue)).check(matches(withText(nameToBetyped)))
        onView(withId(R.id.descriptionValue)).check(matches(withText(descriptionToBetyped)))
        onView(withId(R.id.levelValue)).check(matches(withText(levelToBetyped)))
        onView(withId(R.id.questMapButton)).perform(click())
        intended(hasComponent(QuestMap::class.java.getName()))
        try {
            onView(withId(R.id.questCreateButton)).perform(click())
        } catch (e : AssertionFailedError) {
        }
    }
}