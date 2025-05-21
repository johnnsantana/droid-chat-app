package com.johnnsantana.droidchat.data.network.di

import com.johnnsantana.droidchat.data.manager.token.TokenManager
import com.johnnsantana.droidchat.model.NetworkException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import kotlin.text.append
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.plugin
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        tokenManager: TokenManager
    ): HttpClient {
        return HttpClient(CIO) {
            expectSuccess = true

            install (Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            defaultRequest {
                url("https://chat-api.androidmoderno.com.br/")
                contentType(ContentType.Application.Json)
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, request ->
                    throw if (cause is ClientRequestException) {
                        val errorMessage = cause.response.bodyAsText()
                        NetworkException.ApiException(errorMessage, cause.response.status.value)
                    } else {
                        NetworkException.UnknownNetworkException(cause)
                    }
                }
            }
        }.apply {
            plugin(HttpSend).intercept { request ->
                val accessToken = tokenManager.accessToken.firstOrNull()
                accessToken?.let {
                    request.headers.append("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJ1c2VycyIsImlzcyI6Imh0dHA6Ly8wLjAuMC4wOjgwODAiLCJleHAiOjE3NzkyODAxODksInVzZXJJZCI6Ijc4In0.apKSJW1uikqQq2sr39Ur0Yis2XejH4eAuaWDFh4vwSQ")
                }
                execute(request)
            }
        }
    }
}