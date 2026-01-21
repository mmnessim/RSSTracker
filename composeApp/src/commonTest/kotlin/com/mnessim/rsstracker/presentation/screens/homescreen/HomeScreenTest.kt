package com.mnessim.rsstracker.presentation.screens.homescreen

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import com.mnessim.rsstracker.domain.models.Term
import com.mnessim.rsstracker.domain.repositories.ITermsRepo
import com.mnessim.rsstracker.domain.services.IWorkService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class HomeScreenTest {

    private val testModule = module {
        single<ITermsRepo> { FakeTermsRepo() }
        single<IWorkService> { FakeWorkService() }
    }

    @BeforeTest
    fun setup() {
        startKoin {
            modules(testModule)
        }
    }

    @AfterTest
    fun teardown() {
        stopKoin()
    }

    @Test
    fun homeScreenTest() = runComposeUiTest {
        setContent {
            HomeScreen(onNavigate = {}, onNotificationButton = {}, onNavigateToTiles = {})
        }

        // Basic UI elements with no terms
        onNodeWithTag("AddTermButton").assertExists().performClick()
        waitForIdle()

        // AlertDialog UI
        onNodeWithTag("InitialMessage").assertExists()
        onNodeWithTag("TermAlertDialog").assertExists()
        onNodeWithTag("DismissButton").assertExists()
        onNodeWithText("Test term").assertDoesNotExist()
        onNodeWithTag("TermTextField").assertExists().performTextInput("Test term")
        onNodeWithTag("SubmitButton").assertExists().performClick()

        // After term added
        waitForIdle()
        onNodeWithText("Test term").assertExists()
        onNodeWithTag("InitialMessage").assertDoesNotExist()

        // Lock term and try to delete
        onNodeWithTag("ToggleLockButton").assertExists().performClick()
        onNodeWithTag("TermNotificationsButton").assertExists()
        onNodeWithTag("DeleteButton").assertExists().performClick()
        onNodeWithText("Test term").assertExists()

        // Unlock term and delete
        onNodeWithTag("ToggleLockButton").performClick()
        onNodeWithTag("DeleteButton").performClick()
        waitForIdle()
        onNodeWithText("Test term").assertDoesNotExist()
    }

    @Test
    fun closeAndOpenAlertDialog() = runComposeUiTest {
        setContent {
            HomeScreen(onNavigate = {}, onNotificationButton = {}, onNavigateToTiles = {})
        }
        // No dialog initially
        onNodeWithTag("TermAlertDialog").assertDoesNotExist()
        // Press button, dialog exists
        onNodeWithTag("AddTermButton").assertExists().performClick()
        onNodeWithTag("TermAlertDialog").assertExists()
        onNodeWithTag("DismissButton").assertExists().performClick()
        // Dismiss, dialog does not exist
        onNodeWithTag("TermAlertDialog").assertDoesNotExist()
        // Reopen dialog and enter new term
        onNodeWithTag("AddTermButton").assertExists().performClick()
        onNodeWithTag("TermAlertDialog").assertExists()
        onNodeWithTag("TermTextField").assertExists().performTextInput("Test term")
        onNodeWithTag("SubmitButton").assertExists().performClick()
        onNodeWithTag("TermAlertDialog").assertDoesNotExist()
        // Reopen dialog
        onNodeWithTag("AddTermButton").assertExists().performClick()
        onNodeWithTag("TermAlertDialog").assertExists()
        onNodeWithTag("DismissButton").assertExists().performClick()
        onNodeWithTag("TermAlertDialog").assertDoesNotExist()
        // Delete term, confirm that dialog reopens
        onNodeWithTag("DeleteButton").assertExists().performClick()
        onNodeWithTag("AddTermButton").assertExists().performClick()
        onNodeWithTag("TermAlertDialog").assertExists()
    }
}

class FakeWorkService() : IWorkService, KoinComponent {
    val termsRepo by inject<ITermsRepo>()

    override fun scheduleWork(
        tag: String,
        periodic: Boolean,
        intervalMinutes: Long
    ) {
        TODO("Not yet implemented")
    }

    override fun cancelWork(tag: String) {
        TODO("Not yet implemented")
    }

    override suspend fun performWork(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun refreshWithoutNotification(): Boolean {
        termsRepo.updateTerm(
            Term(
                id = 0,
                term = "Test term",
                locked = false,
                hasNewArticle = true
            )
        )
        return true
    }

}


class FakeTermsRepo : ITermsRepo {
    private val terms = mutableListOf<Term>(
        Term(id = 1L, term = "Test Term", locked = false)
    )

    override fun getAllTerms(): List<Term> = terms.toList()
    override fun getTermById(id: Long): Term? = terms.find { it.id == id }
    override fun insertTerm(term: String, locked: Boolean) {
        val nextId = (terms.maxOfOrNull { it.id } ?: 0L) + 1
        terms.add(Term(id = nextId, term = term, locked = locked))
    }

    override fun updateTerm(term: Term) {
        val idx = terms.indexOfFirst { it.id == term.id }
        if (idx != -1) terms[idx] = term
    }

    override fun deleteTerm(id: Long) {
        terms.removeAll { it.id == id }
    }
}

