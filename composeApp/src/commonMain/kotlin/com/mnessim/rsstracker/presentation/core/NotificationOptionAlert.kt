package com.mnessim.rsstracker.presentation.core

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NotificationOptionAlert(
    modifier: Modifier = Modifier,
    isSnoozed: Boolean,
    onCancel: () -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        confirmButton = {
            Button(
                onClick = onConfirm,
                content = {
                    Text(
                        "Snooze notifications"
                    )
                }
            )
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                content = {
                    Text("Unsnooze notifications")
                }
            )
        },
        text = { if (isSnoozed) Text("Notifications are snoozed") else Text("Notifications are active") }
    )
}