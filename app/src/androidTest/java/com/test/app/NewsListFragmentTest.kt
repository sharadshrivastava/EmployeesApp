package com.test.app


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This class is for Android UI testing using Espresso.
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsListFragmentTest {

    @get:Rule
    val rule = ActivityScenarioRule(EmployeeActivity::class.java)

    @After
    fun cleanup() {
        rule.scenario.close()
    }

    @Test
    fun testProgressBar() {
        onView(withId(R.id.loading)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }

    @Test
    fun testListVisible() {
        Thread.sleep(2000) //to load data
        onView(withId(R.id.employeesList)).check(matches(isDisplayed()))
    }

    //In the same way, using espresso we can test other UI components.
}
