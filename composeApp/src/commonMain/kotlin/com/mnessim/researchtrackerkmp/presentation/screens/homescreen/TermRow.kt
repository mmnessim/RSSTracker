package com.mnessim.researchtrackerkmp.presentation.screens.homescreen

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun TermRow(modifier: Modifier = Modifier, term: String, onDelete: () -> Unit) {
    Row(
        modifier = modifier.testTag("TermRow"),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(term)
        IconButton(
            modifier = modifier.testTag("DeleteButton"),
            onClick = onDelete,
            content = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete term",
                )
            }
        )
    }
}