package com.mnessim.researchtrackerkmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mnessim.Database
import com.mnessim.researchtrackerkmp.presentation.screens.homescreen.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val db = koinInject<Database>()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Research Tracker") }
            )
        }
    ) { innerPadding ->
        HomeScreen(modifier = Modifier.padding(innerPadding).fillMaxSize())
    }


}


