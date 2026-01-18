package com.mnessim.researchtrackerkmp.presentation.screens.aboutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.mnessim.researchtrackerkmp.Constants
import com.mnessim.researchtrackerkmp.domain.models.Stats
import com.mnessim.researchtrackerkmp.domain.services.ApiService
import com.mnessim.researchtrackerkmp.isIos
import io.ktor.client.HttpClient
import org.koin.compose.koinInject

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    val client = koinInject<HttpClient>()
    val apiService = ApiService(client)
    var stats by remember { mutableStateOf<Stats?>(null) }
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(Unit) {
        stats = apiService.getStats()
    }

    Column(
        modifier = modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO handle differences with isIos flag
        if (isIos) {
            AboutTile(
                title = "App Version",
                description = "${Constants.APP_VERSION}/iOS"
            )
        } else {
            AboutTile(
                title = "App Version",
                description = "${Constants.APP_VERSION}/Android"
            )
        }
        AboutTile(
            title = "Developer Information",
            description = "Developed by Mounir Nessim",
            extraText = "Email mnessimdev@gmail.com to provide feedback"
        )

        AboutTile(
            title = "News Sources",
            description = "Articles are fetched from public RSS feeds every 15 minutes and stored for 30 days.",
            extraText = stats?.let { "Current count: ${it.numArticles} articles from ${it.numSources} RSS feeds" }
        )
        Row(
            modifier = Modifier
                .clickable(
                    onClick = {
                        uriHandler.openUri("https://github.com/mmnessim/ResearchTrackerKotlin")
                    }
                )
        ) {
            AboutTile(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable(
                        onClick = {
                            uriHandler.openUri("https://github.com/mmnessim/ResearchTrackerKotlin")
                        }
                    ),
                title = "Source Code",
                description = "View on Github"
            )
        }
        // Maybe add a feedback form or something
    }
}