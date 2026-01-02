package com.mnessim.researchtrackerkmp.domain.services

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HttpClientProvider {
    actual fun getClient(): HttpClient {
        return HttpClient(Darwin)
    }
}