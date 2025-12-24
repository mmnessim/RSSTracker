package com.mnessim.researchtrackerkmp.presentation.screens.detailsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mnessim.researchtrackerkmp.domain.models.Article
import com.mnessim.researchtrackerkmp.domain.models.placeholderArticle
import com.mnessim.researchtrackerkmp.domain.repositories.ITermsRepo
import org.koin.compose.koinInject

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    id: Long
) {
    val repo = koinInject<ITermsRepo>()
    val term = repo.getTermById(id)
    // TODO: include proper implementation when backend is ready
    // will probably look like:
    // val apiService = koinInject<IApiService>()
    // val response = apiService.search(term)
    val articles = listOf(placeholderArticle)

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (term != null) {
            Row(
                modifier = Modifier.fillMaxWidth(.9f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = term.term,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 20.sp
                    )
                )
            } // Row
        } // if (term != null)

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (a in articles) {
                ArticleTile(a)
            }
        }

        Button(onClick = onBack) {
            Text("Back")
        } // Button
    } // Column
}

@Composable
fun ArticleTile(article: Article) {
    val baseFontSize = 16

    Text(
        text = "${article.title} - ${article.creator}",
        style = TextStyle(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = (baseFontSize + 8).sp
        )
    )
    Text(
        text = article.description,
        style = TextStyle(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = (baseFontSize).sp
        )
    )
    for (c in article.categories) {
        Text(
            text = c,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = (baseFontSize).sp
            )
        )
    }
    if (article.mediaContentUrl != null) {
        AsyncImage(
            model = article.mediaContentUrl,
            contentDescription = article.mediaDescription,
            modifier = Modifier
                .fillMaxWidth(.9f)
                .height(150.dp),
            contentScale = ContentScale.Crop
        )
    }
}