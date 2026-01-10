package com.mnessim.researchtrackerkmp.domain.repositories

import com.mnessim.Database
import com.mnessim.researchtrackerkmp.domain.models.Article

class SavedArticlesRepo(private val database: Database) {
    private val queries = database.savedArticlesQueries

    fun getAllArticles(): List<Article> {
        return queries.getAllArticles()
            .executeAsList()
            .map { article ->
                Article(
                    id = article.id,
                    rssSource = article.rssSource,
                    title = article.title,
                    link = article.link,
                    guid = article.guid,
                    description = article.description,
                    pubDate = article.pubDate,
                    pubDateMs = article.pubDateMs,
                    categories = emptyList() // TODO: add helper to convert string to list of strings
                )
            }
    }

    fun insertArticle(article: Article) {
        queries.insertArtricle(
            rssSource = article.rssSource,
            title = article.title,
            link = article.link,
            guid = article.guid,
            description = article.description,
            pubDate = article.pubDate,
            pubDateMs = article.pubDateMs,
            categories = "" // TODO: add helper to convert list of strings to string
        )
    }

    fun deleteArticle(title: String) {
        queries.deleteArticle(title)
    }

    fun getOneArticle(guid: String?): Article? {
        if (guid == null) return null;
        try {
            val list = queries
                .getOneArticle(guid)
                .executeAsList()
            val result = list.first()

            return Article(
                id = result.id,
                rssSource = result.rssSource,
                title = result.title,
                link = result.link,
                guid = result.guid,
                description = result.description,
                pubDate = result.pubDate,
                pubDateMs = result.pubDateMs,
                categories = emptyList()
            )
        } catch (e: Exception) {
            println("Error: $e")
            return null
        }
    }
}