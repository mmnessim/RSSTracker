package com.mnessim.rsstracker.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: Long?,
    val rssSource: String,
    val title: String,
    val link: String,
    val guid: String? = null,
    val description: String? = null,
    // TODO: when switching to Rust backend, this will be millis since epoch instead of string
    val pubDate: String? = null,
    val pubDateMs: Long? = null,
    val categories: List<String> = emptyList(),
)
