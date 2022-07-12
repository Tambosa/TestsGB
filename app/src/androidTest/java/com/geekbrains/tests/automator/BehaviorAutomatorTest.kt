package com.geekbrains.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorAutomatorTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName

    @Before
    fun setup() {
        uiDevice.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        context.startActivity(intent)
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun test_MainActivityIsStarted() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        Assert.assertNotNull(editText)
    }

    @Test
    fun test_SearchIsPositive() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "UiAutomator"
        val searchButton: UiObject2 = uiDevice.findObject(
            By.res(packageName, "searchButton")
        )
        searchButton.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        Assert.assertEquals("Number of results: 491266", changedText.text.toString())
    }

    @Test
    fun test_OpenDetailsScreen() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
        toDetails.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        Assert.assertEquals(changedText.text, "Number of results: 0")
    }

    @Test
    fun test_OpenDetailsScreen_WithCorrectSearchNumber() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "UiAutomator"
        val searchButton: UiObject2 = uiDevice.findObject(
            By.res(packageName, "searchButton")
        )
        searchButton.click()

        val totalCountSearch =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        val totalCountSearchText: String = totalCountSearch.text

        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
        toDetails.click()

        val totalCountDetails =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        Assert.assertEquals(totalCountSearchText, totalCountDetails.text)
    }

    @Test
    fun test_DetailsScreenIncrement() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
        toDetails.click()

        val incrementButton = uiDevice.wait(
            Until.findObject(By.res(packageName, "incrementButton")),
            TIMEOUT
        )
        incrementButton.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        Assert.assertEquals(changedText.text, "Number of results: 1")
    }

    @Test
    fun test_DetailsScreenDecrement() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
        toDetails.click()

        val decrementButton = uiDevice.wait(
            Until.findObject(By.res(packageName, "decrementButton")),
            TIMEOUT
        )
        decrementButton.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )
        Assert.assertEquals(changedText.text, "Number of results: -1")
    }

    companion object {
        private const val TIMEOUT = 5000L
    }
}