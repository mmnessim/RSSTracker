package com.mnessim.researchtrackerkmp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val rssSource: String,
    val title: String,
    val link: String,
    val guid: String? = null,
    val description: String? = null,
    val pubDate: String? = null,
    val categories: List<String> = emptyList(),
)
