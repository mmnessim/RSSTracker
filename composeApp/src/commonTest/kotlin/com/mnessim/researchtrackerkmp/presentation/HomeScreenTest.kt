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
        // Basic UI elements with no terms
        onNodeWithTag("Title").assertExists()
        onNodeWithTag("AddTermButton").assertExists().performClick()
        // AlertDialog UI
        onNodeWithTag("TermAlertDialog").assertExists()
        onNodeWithTag("DismissButton").assertExists()
        onNodeWithText("Test term").assertDoesNotExist()
        onNodeWithTag("TermTextField").assertExists().performTextInput("Test term")
        onNodeWithTag("SubmitButton").assertExists().performClick()
        // After term added
        onNodeWithText("Test term").assertExists()
        // Lock term and try to delete
        onNodeWithTag("ToggleLockButton").assertExists().performClick()
        onNodeWithTag("TermNotificationsButton").assertExists()
        onNodeWithTag("DeleteButton").assertExists().performClick()
        onNodeWithText("Test term").assertExists()
        // Unlock term and try to delete
        onNodeWithTag("ToggleLockButton").performClick()
        onNodeWithTag("DeleteButton").performClick()
        onNodeWithText("Test term").assertDoesNotExist()
    }
}