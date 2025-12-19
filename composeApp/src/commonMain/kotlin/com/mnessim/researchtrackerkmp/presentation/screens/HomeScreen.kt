package com.mnessim.researchtrackerkmp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mnessim.researchtrackerkmp.AddTermAlert
import com.mnessim.researchtrackerkmp.AddTermButton

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val terms = remember { mutableStateListOf<String>() }
    val textFieldState = remember { TextFieldState() }
    var showAlertDialog by remember { mutableStateOf(false) }
    MaterialTheme {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Research Tracker",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
            for (term in terms) {
                Text(term)
            }
            AddTermButton(
                onClick = { showAlertDialog = true }
            )
            if (showAlertDialog) {
                AddTermAlert(
                    textFieldState = textFieldState,
                    onSubmit = {
                        val input = textFieldState.text.toString()
                        if (input.isNotEmpty()) {
                            terms.add(input)
                            textFieldState.clearText()
                        }
                        showAlertDialog = false
                    },
                    onDismiss = { showAlertDialog = false },
                    tag = "TermAlertDialog"
                )
            }
        }
    }
}