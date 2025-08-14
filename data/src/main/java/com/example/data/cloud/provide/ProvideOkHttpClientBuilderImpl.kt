package com.example.data.cloud.provide

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ProvideOkHttpClientBuilderImpl(
    private val provideInterceptor: ProvideInterceptor,
    private val timeOutSeconds: Long = TIME_OUT_SECONDS,
) : ProvideOkHttpClientBuilder {
    override fun httpClientBuilder(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(provideInterceptor.requestInterceptor())
            .addInterceptor(provideInterceptor.loggingInterceptor())
            .connectTimeout(timeOutSeconds, TimeUnit.SECONDS)
            .readTimeout(timeOutSeconds, TimeUnit.SECONDS)
            .build()

    companion object {
        const val TIME_OUT_SECONDS = 60L
    }
}