package com.mnessim.rsstracker.presentation.screens.detailsscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnessim.rsstracker.domain.repositories.ITermsRepo
import com.mnessim.rsstracker.domain.repositories.PreferencesRepo
import com.mnessim.rsstracker.domain.services.ApiService
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    id: Long,
    onError: () -> Unit,
) {
    val repo = koinInject<ITermsRepo>()
    val client = koinInject<HttpClient>()
    val apiService = ApiService(client)
    val prefsRepo = koinInject<PreferencesRepo>()
    val viewModel = remember(id) {
        DetailsScreenViewModel(
            apiService = apiService,
            id = id,
            termsRepo = repo,
            prefsRepo = prefsRepo
        )
    }

    val articles by viewModel.response.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val blocked by viewModel.blocked.collectAsState()
    val numBlocked by viewModel.numBlocked.collectAsState()
    val term = viewModel.term
    var overrideBlock by remember { mutableStateOf(false) }

    if (term.id == -1L) {
        onError()
    }

    var showAmount by remember { mutableStateOf(50) }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = id) {
        viewModel.fetch()
    }

    LaunchedEffect(articles) {
        viewModel.getNumBlocked()
    }

    Column(
        modifier = modifier
            .fillMaxSize()

            .testTag("DetailsScreenOuterColumn"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(.9f)
                .testTag("DetailsScreenTermRow"),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.testTag("Term"),
                text = "${term.term} - ${articles.size} Results",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (overrideBlock) "Showing $numBlocked blocked articles" else "$numBlocked blocked articles",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Button(
                onClick = { overrideBlock = !overrideBlock },
                content = {
                    Text(
                        text = if (overrideBlock) "Hide" else "Show",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            )
        }
        // TODO: Add button to show anyways

        if (!loading) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .fillMaxWidth()
                    .testTag("ArticlesColumn"),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(
                    articles.take(showAmount),
                    key = { index, a -> "${a.guid ?: a.link}-$index" }
                ) { i, a ->
                    val isBlocked = blocked.contains(a.rssSource)

                    if (!isBlocked || overrideBlock) {
                        ArticleTile(
                            modifier = Modifier.testTag("ArticleTile"),
                            article = a,
                        )
                    }
                }
            }
        } else {
            Box(modifier = Modifier.weight(1f)) {}
        }

        Row {
            Button(onClick = {
                val old = showAmount
                showAmount += 10

                val newly = articles.drop(old).take(showAmount - old).map { it.guid }
                if (newly.isNotEmpty()) {
                    coroutineScope.launch {
                        listState.animateScrollToItem(old)
                    }
                }
            }) {
                Text("Show more")
            }
            Button(onClick = onBack, modifier = Modifier.testTag("BackButton")) {
                Text("Back")
            }
        }

        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.testTag("DetailsScreenLoading"))
            }
        }
    }
}
