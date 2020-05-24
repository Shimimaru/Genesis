package com.example.Genesis

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.Genesis.database.Database
import com.example.Genesis.menu.Quest.QuestMap.QuestMap
import com.example.Genesis.menu.Quest.QuestMenu.QuestMenu
import com.example.Genesis.menu.Quest.QuestsCreate.QuestCreateMenu
import junit.framework.AssertionFailedError
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.properties.Delegates

class QuestTest {
        private lateinit var nameToBetyped: String
        private lateinit var descriptionToBetyped: String
        @get:Rule
        var intentRule: IntentsTestRule<QuestMenu> = IntentsTestRule(QuestMenu::class.java)

        @Before
        fun initValidString() {
            nameToBetyped = "QuestName"
            descriptionToBetyped = "Description"

        }

        @Test
        fun mainTest() {
            onView(ViewMatchers.withId(R.id.questNameValue)).check(ViewAssertions.matches(ViewMatchers.withText(nameToBetyped)))
            onView(ViewMatchers.withId(R.id.questDescriptionValue)).check(ViewAssertions.matches(ViewMatchers.withText(descriptionToBetyped)))
            onView(ViewMatchers.withId(R.id.questCreateButton)).perform(ViewActions.click())
            intended(hasComponent(QuestCreateMenu::class.java.getName()))
            onView(ViewMatchers.withId(R.id.questGenerateButton)).perform(ViewActions.click())
            onView(ViewMatchers.withId(R.id.questAbandonButton)).perform(ViewActions.click())
            onView(ViewMatchers.withId(R.id.questTrackButton)).perform(ViewActions.click())
            onView(ViewMatchers.withId(R.id.listView)).perform(ViewActions.click())
            Intents.intended(IntentMatchers.hasComponent(QuestMap::class.java.getName()))
        }
}