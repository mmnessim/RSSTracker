package com.mnessim.researchtrackerkmp.presentation.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.mnessim.researchtrackerkmp.presentation.theme.darkScheme
import com.mnessim.researchtrackerkmp.presentation.theme.highContrastDarkColorScheme
import com.mnessim.researchtrackerkmp.presentation.theme.highContrastLightColorScheme
import com.mnessim.researchtrackerkmp.presentation.theme.lightScheme

@Composable
fun ColorSchemeDialog(
    activeScheme: ColorScheme,
    onDismiss: () -> Unit,
    onColorSchemeChange: (String) -> Unit,
) {
    val activeBackground = ButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.secondary,
        disabledContentColor = MaterialTheme.colorScheme.onSecondary
    )

    val inactiveBackground = ButtonColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.inverseSurface,
        disabledContentColor = MaterialTheme.colorScheme.inverseOnSurface,
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Choose Color Scheme") },
        text = { Text("Select an option") },
        confirmButton = {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                TextButton(
                    onClick = { onColorSchemeChange("light") },
                    colors = if (activeScheme == lightScheme) activeBackground else inactiveBackground
                ) {
                    Text("Light")
                } // TextButton
                TextButton(
                    onClick = { onColorSchemeChange("dark") },
                    colors = if (activeScheme == darkScheme) activeBackground else inactiveBackground

                ) {
                    Text("Dark")
                } // TextButton
            } // Row
        }, // confirmButton =
        dismissButton = {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                TextButton(
                    onClick = { onColorSchemeChange("lightContrast") },
                    colors = if (activeScheme == highContrastLightColorScheme) activeBackground else inactiveBackground
                ) {
                    Text("Light Contrast")
                } // TextButton
                TextButton(
                    onClick = { onColorSchemeChange("darkContrast") },
                    colors = if (activeScheme == highContrastDarkColorScheme) activeBackground else inactiveBackground
                ) {
                    Text("Dark Contrast")
                } // TextButton
            } // Row
        } // dismissButton =
    ) // AlertDialog
} // ColorSchemeDialog