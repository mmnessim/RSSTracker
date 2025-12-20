package com.mnessim.researchtrackerkmp

import androidx.compose.ui.window.ComposeUIViewController
import com.mnessim.researchtrackerkmp.domain.data.DBFactory
import com.mnessim.researchtrackerkmp.domain.data.createDatabase

fun MainViewController() = ComposeUIViewController {
    val database = createDatabase(DBFactory())
    App(database)
}