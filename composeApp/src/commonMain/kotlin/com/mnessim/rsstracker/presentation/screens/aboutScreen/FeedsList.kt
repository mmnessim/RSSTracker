package com.mnessim.rsstracker.presentation.screens.aboutScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnessim.rsstracker.domain.services.ApiService
import io.ktor.client.HttpClient
import org.koin.compose.koinInject

@Composable
fun FeedsList(
    modifier: Modifier = Modifier
) {
    val client = koinInject<HttpClient>()
    val apiService = ApiService(client)
    val feeds = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        val response = apiService.getFeedsList()
        feeds.addAll(response)
    }

    Column(
        modifier = modifier
            .border(
                color = MaterialTheme.colorScheme.primary,
                width = 2.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
    ) {
        Text(
            text = "RSS Sources",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Several sources, such at the New York Times, publish several RSS feeds",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = feeds) { it ->
                Text(
                    text = it,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }

}