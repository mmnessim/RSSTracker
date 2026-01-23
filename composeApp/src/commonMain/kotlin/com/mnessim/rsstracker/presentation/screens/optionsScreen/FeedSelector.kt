package com.mnessim.rsstracker.presentation.screens.optionsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeedSelector(
    modifier: Modifier = Modifier,
    feeds: List<String>,
    blockedFeeds: List<String> = emptyList(),
    toggleBlock: (String) -> Unit = {},
) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
        text = "Block results from specific sources",
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    )
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = feeds,
            key = { it -> it }
        ) {
            val blocked = blockedFeeds.contains(it)

            Surface(
                modifier = Modifier.fillMaxWidth()
                    .clickable(
                        onClick = { toggleBlock(it) }
                    ),
                color = if (blocked) Color.Red else MaterialTheme.colorScheme.background,
            ) {
                Text(
                    "$it ${if (blocked) "BLOCKED" else ""}",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}