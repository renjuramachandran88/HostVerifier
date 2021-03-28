package com.ml.hostverifier.home

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.Intent
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.ml.hostverifier.R
import com.ml.hostverifier.home.HomeActivity.Companion.DOCUMENT_READER_REQUEST_CODE
import com.ml.hostverifier.home.HomeActivity.IntentExtra.VERIFIER_DATA_LIST
import com.ml.hostverifier.splash.VerifierData
import com.ml.verifier.documentreader.CameraPreviewActivity
import com.ml.verifier.documentreader.CameraPreviewActivity.Companion.IntentExtra.DOCUMENT_RESULT
import com.ml.verifier.documentreader.DocumentResult.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java, false, false)

    private val homeActivityRobot = HomeActivityRobot()
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
    fun onActivityLaunched_givenEmptyList_seesHomeScreen() {
        launchActivity(emptyList())

        homeActivityRobot.seesRecyclerList()
            .seesNoDataText(0, context.getString(R.string.no_data))
            .seesVerifyButton(1, context.getString(R.string.add_data))
            .seesClearButton(2, context.getString(R.string.clear_data))
    }

    @Test
    fun onActivityLaunched_givenVerifiedList_seesHomeScreen() {
        launchActivity(listOf(
            VerifierData("test 1"),
            VerifierData("test 2"),
            VerifierData("test 3")
        ))

        homeActivityRobot.seesRecyclerList()
            .seesVerifiedDataText(0, "test 1")
            .seesVerifiedDataText(1, "test 2")
            .seesVerifiedDataText(2, "test 3")
            .seesVerifyButton(3, context.getString(R.string.add_data))
            .seesClearButton(4, context.getString(R.string.clear_data))

    }

    @Test
    fun onActivityLaunched_givenEmptyListAndClicksAddButton_seesHomeScreen() {
        launchActivity(emptyList())

        homeActivityRobot
            .seesRecyclerList()
            .seesNoDataText(0, context.getString(R.string.no_data))
            .seesVerifyButton(1, context.getString(R.string.add_data))
            .seesClearButton(2, context.getString(R.string.clear_data))
            .clicksVerifyButton(1, context.getString(R.string.add_data))

        launchActivityWithResult()

        homeActivityRobot
            .seesRecyclerList()
            .seesVerifiedDataText(0, "test 1")
            .seesVerifyButton(1, context.getString(R.string.add_data))
            .seesClearButton(2, context.getString(R.string.clear_data))
            .clicksClearButton(2, context.getString(R.string.clear_data))
    }

    @Test
    fun onActivityLaunched_givenVerifiedListAndClicksClearButton_seesHomeScreen() {
        launchActivity(listOf(
            VerifierData("test 1"),
            VerifierData("test 2"),
            VerifierData("test 3")
        ))

        homeActivityRobot.seesRecyclerList()
            .seesVerifiedDataText(0, "test 1")
            .seesVerifiedDataText(1, "test 2")
            .seesVerifiedDataText(2, "test 3")
            .seesVerifyButton(3, context.getString(R.string.add_data))
            .seesClearButton(4, context.getString(R.string.clear_data))
            .clicksClearButton(4, context.getString(R.string.clear_data))
            .seesNoDataText(0, context.getString(R.string.no_data))
    }

    private fun launchActivity(verifiedDatalist: List<VerifierData>) {
        val intent = Intent(context, HomeActivity::class.java)
        intent.putParcelableArrayListExtra(VERIFIER_DATA_LIST, ArrayList(verifiedDatalist))
        activityRule.launchActivity(intent)
    }

    private fun launchActivityWithResult(){
        val dataIntent = Intent()
        dataIntent.putExtra(DOCUMENT_RESULT, Success("test 1"))

        intending(hasComponent(CameraPreviewActivity::class.java.name))
            .respondWith(ActivityResult(RESULT_OK, dataIntent))

        activityRule.activity.startActivityForResult(
            Intent(activityRule.activity, CameraPreviewActivity::class.java),
            DOCUMENT_READER_REQUEST_CODE
        )
    }

}