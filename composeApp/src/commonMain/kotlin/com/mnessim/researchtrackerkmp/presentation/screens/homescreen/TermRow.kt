package com.mnessim.researchtrackerkmp.presentation.screens.homescreen

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun TermRow(modifier: Modifier = Modifier, term: String, onDelete: () -> Unit) {
    var locked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.testTag("TermRow"),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(term)
        IconButton(
            modifier = Modifier.testTag("ToggleLockButton"),
            onClick = { locked = !locked },
            content = {
                Icon(
                    imageVector = if (locked) Icons.Default.Lock else Icons.Default.LockOpen,
                    contentDescription = "Toggle Locked"
                )
            })
        IconButton(
            modifier = Modifier.testTag("TermNotificationsButton"),
            onClick = {},
            content = {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Manage Notifications"
                )
            })
        IconButton(
            modifier = Modifier.testTag("DeleteButton"),
            onClick = {
                if (!locked) onDelete()
            },
            content = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete term",
                )
            }
        )
    }
}