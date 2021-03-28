package com.ml.hostverifier.splash

import android.content.Context
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.ml.hostverifier.home.HomeActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java, false, false)

    private val splashActivityRobot = SplashActivityRobot()
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun onLaunched_seesSplashActivity() {
        activityRule.launchActivity(null)

        splashActivityRobot
            .seesSplashImage()
            .seesSplashText()
            .seesSplashLoadingDialog()
    }
}