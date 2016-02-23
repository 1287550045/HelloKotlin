package com.chuanxi.hellokotlin

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

/**
 * Created by tangjunjie on 2016/2/23.
 */
class SimpleInstrumentationTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test fun itemClick_navigatesToDetail() {
        onView(withId(R.id.forecast_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(6, click()))
        onView(withId(R.id.weatherDescription)).check(matches(isAssignableFrom(TextView::class.java)))
    }

    @Test fun modifyZipCode_changesToolbarTitle() {
        openActionBarOverflowOrOptionsMenu(activityRule.activity)
        onView(withText(R.string.settings)).perform(click())
        onView(withId(R.id.cityCode)).perform(replaceText("28830"))
        Espresso.pressBack()
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Matchers.`is`("San Fernando de Henares (ES)"))))
    }

    fun withToolbarTitle(textMatcher:Matcher<CharSequence>):Matcher<Any> = object :BoundedMatcher<Any,Toolbar>(Toolbar::class.java) {
        override fun matchesSafely(toolbar: Toolbar): Boolean {
            return textMatcher.matches(toolbar.title)
        }

        override fun describeTo(description: Description) {
            description.appendText("with toolbar title: ")
            textMatcher.describeTo(description)
        }
    }
}
