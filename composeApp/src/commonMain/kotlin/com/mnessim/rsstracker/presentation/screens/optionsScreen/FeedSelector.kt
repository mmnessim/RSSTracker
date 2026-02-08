package com.mnessim.rsstracker.presentation.screens.optionsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FeedSelector(
    modifier: Modifier = Modifier,
    feeds: List<String>,
    blockedFeeds: List<String> = emptyList(),
    toggleBlock: (String) -> Unit = {},
) {
    var showAlertDialog by remember { mutableStateOf(false) }

    Surface(
        shadowElevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                text = "Block results from specific sources",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
            )
            if (blockedFeeds.size > 1) {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                    text = if (blockedFeeds.size == 2) "${blockedFeeds.size - 1} feed blocked" else "${blockedFeeds.size - 1} feeds blocked",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                )
            }
            ElevatedButton(
                onClick = {
                    showAlertDialog = true
                },
                content = {
                    Text(
                        text = "Unblock all",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = modifier.height(500.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = feeds,
                    key = { it -> it }
                ) {
                    val blocked = blockedFeeds.contains(it)

                    Surface(
                        shadowElevation = 8.dp,
                        modifier = Modifier.fillMaxWidth()
                            .clickable(
                                onClick = { toggleBlock(it) }
                            ),
                        color = if (blocked)
                            MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "$it ${if (blocked) "BLOCKED" else ""}",
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = if (blocked)
                                    MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onSecondaryContainer,
                                fontWeight = if (blocked) FontWeight.Bold else FontWeight.Medium
                            )
                        )
                    }
                }
            }
        }
    }

    if (showAlertDialog) {
        UnblockAlert(
            onConfirm = {
                showAlertDialog = false
                for (f in blockedFeeds) {
                    toggleBlock(f)
                }
            },
            onDismiss = {
                showAlertDialog = false
            }
        )
    }
}

@Composable
fun UnblockAlert(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Dismiss")
            }
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text("Confirm")
            }
        },
        title = { Text("Unblock all feeds?") }
    )
}