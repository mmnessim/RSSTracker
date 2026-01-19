package com.mnessim.rsstracker.domain.services

import io.ktor.client.HttpClient

expect class HttpClientProvider() {
    fun getClient(): HttpClient
}