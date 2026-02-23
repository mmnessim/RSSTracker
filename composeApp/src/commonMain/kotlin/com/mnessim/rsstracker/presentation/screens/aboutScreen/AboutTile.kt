package com.mnessim.rsstracker.presentation.screens.aboutScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AboutTile(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    extraText: String? = null,
    shadowColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    Surface(
        shadowElevation = 4.dp,
        color = shadowColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        SelectionContainer {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                if (extraText != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = extraText,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    AboutTile(
        title = "About this app",
        description = "Developed by Mounir Nessim",
        extraText = "Email mnessimdev@gmail.com"
    )
}
