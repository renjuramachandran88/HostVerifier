package com.ml.hostverifier.splash

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.ml.hostverifier.R

class SplashActivityRobot {
    fun seesSplashImage(): SplashActivityRobot {
        onView(withId(R.id.splash_welcome_image))
            .check(matches(isDisplayed()))
        return this
    }

    fun seesSplashText(): SplashActivityRobot {
        onView(withId(R.id.splash_welcome_text))
            .check(matches(isDisplayed()))
        return this
    }

    fun seesSplashLoadingDialog(): SplashActivityRobot {
        onView(withId(R.id.splash_welcome_progress))
            .check(matches(isDisplayed()))
        return this
    }


}