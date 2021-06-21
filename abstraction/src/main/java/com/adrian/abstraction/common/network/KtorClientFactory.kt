package com.adrian.abstraction.common.network

import io.ktor.client.*

interface KtorClientFactory {
    fun createClient(): HttpClient
}