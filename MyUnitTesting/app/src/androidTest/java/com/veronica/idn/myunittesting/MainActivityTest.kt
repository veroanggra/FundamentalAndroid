package com.veronica.idn.myunittesting

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MainActivityTest {
    private val dummyVolume = "504.0"
    private val dummyCircumference = "100.0"
    private val dummySurfaceArea = "396.0"
    private val dummyLength = "12.0"
    private val dummyWidth = "7.0"
    private val dummyHeight = "6.0"
    private val emptyInput = ""
    private val fieldEmpty = "Field ini tidak boleh kosong"

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertGetCircumference() {
        onView(withId(R.id.et_length)).perform(typeText(dummyLength), closeSoftKeyboard())
        onView(withId(R.id.et_heigth)).perform(typeText(dummyHeight), closeSoftKeyboard())
        onView(withId(R.id.et_width)).perform(typeText(dummyWidth), closeSoftKeyboard())

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())

        onView(withId(R.id.btn_circumference)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_circumference)).perform(click())


        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dummyCircumference)))
    }

    @Test
    fun getCuboidViewModel() {
    }

    @Test
    fun setCuboidViewModel() {
    }

    @Test
    fun onCreate() {
    }

    @Test
    fun onClick() {
    }
}