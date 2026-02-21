package com.mnessim.rsstracker.presentation.screens.savedArticlesScreen

import androidx.lifecycle.ViewModel
import com.mnessim.rsstracker.domain.models.Article
import com.mnessim.rsstracker.domain.repositories.SavedArticlesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SavedArticlesViewModel(
    savedArticlesRepo: SavedArticlesRepo
) : ViewModel() {
    private var _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()

    init {
        _articles.value = savedArticlesRepo.getAllArticles()
        sort("new")
    }

    fun sort(by: String) {
        when (by) {
            "new" -> _articles.value =
                _articles.value.sortedByDescending { article -> article.pubDateMs }

            "old" -> _articles.value = _articles.value.sortedBy { article -> article.pubDateMs }

            else -> return
        }
    }
}