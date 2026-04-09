package com.mnessim.rsstracker.presentation.screens.aboutScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnessim.rsstracker.domain.services.ApiService
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import kotlin.random.Random

@Composable
fun HealthCheck(modifier: Modifier = Modifier) {
    val client = koinInject<HttpClient>()
    val apiService = ApiService(client)

    var status by remember { mutableStateOf(HttpStatusCode.InternalServerError) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val healthyInterval = 5 * 60_000L
        val unhealthyBase = 500L
        val maxBackoff = 5 * 60_000L
        var currentInterval = unhealthyBase
        var consecutiveFailures = 0

        while (isActive) {
            try {
                val result = apiService.checkHealth()
                status = result

                if (result == HttpStatusCode.OK) {
                    currentInterval = healthyInterval
                    consecutiveFailures = 0
                } else {
                    consecutiveFailures++
                    currentInterval = (unhealthyBase * (1L shl (consecutiveFailures - 1)))
                        .coerceAtMost(maxBackoff)
                }
            } catch (t: Throwable) {
                consecutiveFailures++
                currentInterval = (unhealthyBase * (1L shl (consecutiveFailures - 1)))
                    .coerceAtMost(maxBackoff)
                status = HttpStatusCode.InternalServerError
            }

            val jitter = (currentInterval * 0.1).toLong().coerceAtLeast(0L)
            val delayMs = currentInterval + Random.nextLong(-jitter, jitter + 1)
            delay(delayMs)
        }
    }

    val isHealthy = status == HttpStatusCode.OK
    val isChecking = status == HttpStatusCode.Processing
    val statusText = when {
        isChecking -> "Checking..."
        isHealthy -> "Healthy"
        else -> "Unavailable"
    }
    val dotColor = when {
        isChecking -> Color.Gray
        isHealthy -> Color.Green
        else -> Color.Red
    }

    Surface(
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(12.dp),
        onClick = {
            status = HttpStatusCode.Processing
            scope.launch {
                status = apiService.checkHealth()
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Server Status",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = dotColor,
                    shape = CircleShape,
                    modifier = Modifier.size(10.dp)
                ) {}
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = statusText,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}
