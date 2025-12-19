package com.mnessim.researchtrackerkmp.presentation.screens.homescreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag

@Composable
fun AddTermButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tag: String = "AddTermButton"
) {
    IconButton(onClick = onClick, modifier = modifier.semantics { testTag = tag }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add new term"
        )
    }
}