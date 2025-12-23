package com.mnessim.researchtrackerkmp.presentation.core

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.InvertColors
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    colorScheme: ColorScheme,
    canPop: Boolean,
    onNavigate: () -> Unit,
    onChangeColorScheme: () -> Unit,
    onNotificationButton: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarColors(
            containerColor = colorScheme.primaryContainer,
            scrolledContainerColor = colorScheme.primaryContainer,
            navigationIconContentColor = colorScheme.onPrimaryContainer,
            titleContentColor = colorScheme.onPrimaryContainer,
            actionIconContentColor = colorScheme.onPrimaryContainer
        ), // colors =
        title = { Text("Research Tracker") },
        navigationIcon = if (canPop) {
            {
                IconButton(onClick = onNavigate) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        } else {
            {}
        }, // navigationIcon =
        actions = {
            IconButton(onClick = onNotificationButton) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Test Notifications"
                ) // Icon
            } // IconButton
            Surface(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                onChangeColorScheme()
                            },
                        )
                    },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.InvertColors,
                    contentDescription = "Toggle Dark Mode"
                ) // Icon
            } // Surface
        }
    )
}