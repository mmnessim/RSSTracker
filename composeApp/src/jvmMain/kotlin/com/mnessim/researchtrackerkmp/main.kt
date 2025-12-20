package com.mnessim.researchtrackerkmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mnessim.researchtrackerkmp.domain.data.DBFactory
import com.mnessim.researchtrackerkmp.domain.data.createDatabase

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ResearchTrackerKMP",
    ) {
        val database = createDatabase(DBFactory())
        App(database)
    }
}