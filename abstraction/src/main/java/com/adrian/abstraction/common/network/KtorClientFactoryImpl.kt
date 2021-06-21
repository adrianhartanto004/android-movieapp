package com.adrian.abstraction.common.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@OptIn(KoinApiExtension::class)
class KtorClientFactoryImpl : KtorClientFactory, KoinComponent {

    override fun createClient(): HttpClient {
        val nonStrictJson =
            Json { isLenient = true; ignoreUnknownKeys = true }
        return HttpClient(CIO) {
            defaultRequest {
//                url.host = UrlProvider.BASE_URL
                url.protocol = URLProtocol.HTTPS
//                this.parameter(API_PARAM, API_KEY)
            }

            expectSuccess = false

            HttpResponseValidator {
                validateResponse { response ->
//                    if (response.status.value >= 300) {
//                        throw HttpResponseException(response, response.status)
//                    }
                }
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(nonStrictJson)
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
}