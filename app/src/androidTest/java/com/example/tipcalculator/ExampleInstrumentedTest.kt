package com.example.tipcalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tipcalculator", appContext.packageName)
    }

    @Test
    fun testCalculateTip() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TipCalculatorApp()
                }
            }
        }
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextClearance()
        Thread.sleep(1000)
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")
        Thread.sleep(1000)
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextClearance()
        Thread.sleep(1000)
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")
        Thread.sleep(1000)
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }
}