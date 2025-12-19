package com.mnessim.researchtrackerkmp.presentation

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import com.mnessim.researchtrackerkmp.presentation.screens.homescreen.HomeScreen
import kotlin.test.Test

class HomeScreenTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun homeScreenTest() = runComposeUiTest {
        setContent {
            HomeScreen()
        }
        onNodeWithTag("Title").assertExists()
        onNodeWithTag("AddTermButton").assertExists().performClick()
        onNodeWithTag("TermAlertDialog").assertExists()
        onNodeWithText("Test term").assertDoesNotExist()
        onNodeWithTag("TermTextField").assertExists().performTextInput("Test term")
        onNodeWithTag("SubmitButton").assertExists().performClick()
        onNodeWithText("Test term").assertExists()
        onNodeWithTag("DeleteButton").assertExists().performClick()
        onNodeWithText("Test term").assertDoesNotExist()
    }
}