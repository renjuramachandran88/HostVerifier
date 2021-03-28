package com.ml.hostverifier.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.ml.hostverifier.CustomMatchers.atPositionOnView
import com.ml.hostverifier.R


class HomeActivityRobot {
    fun seesRecyclerList(): HomeActivityRobot {
        onView(withId(R.id.recycler_view))
            .check(matches(ViewMatchers.isDisplayed()))
        return this
    }

    fun seesVerifiedDataText(position: Int, text: String): HomeActivityRobot {
        onView(withId(R.id.recycler_view))
            .check(matches(atPositionOnView(position, withText(text), R.id.home_verified_data_text)))
        return this
    }

    fun seesNoDataText(position: Int, text: String): HomeActivityRobot {
        onView(withId(R.id.recycler_view))
            .check(matches(atPositionOnView(position, withText(text), R.id.home_no_data_text)))
        return this
    }

    fun seesVerifyButton(position: Int, text: String): HomeActivityRobot {
        onView(withId(R.id.recycler_view))
            .check(
                matches(atPositionOnView(position, withText(text), R.id.home_add_button
                    )
                )
            )
        return this
    }

    fun seesClearButton(position: Int, text: String): HomeActivityRobot {
        onView(withId(R.id.recycler_view))
            .check(
                matches(
                    atPositionOnView(
                        position, withText(text), R.id.home_clear_button
                    )
                )
            )
        return this
    }

    fun clicksVerifyButton(position: Int, breed: String): HomeActivityRobot {
        onView(withId(R.id.recycler_view))
            .check(
                matches(atPositionOnView(position, withText(breed), R.id.home_add_button))
            )
            .perform(ViewActions.click())
        return this
    }

    fun clicksClearButton(position: Int, breed: String): HomeActivityRobot {
        onView(withId(R.id.recycler_view))
            .check(
                matches(atPositionOnView(position, withText(breed), R.id.home_clear_button))
            )
            .perform(ViewActions.click())
        return this
    }
}