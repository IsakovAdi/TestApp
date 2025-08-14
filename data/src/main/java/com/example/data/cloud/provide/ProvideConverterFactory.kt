package com.example.data.cloud.provide

import retrofit2.Converter

interface ProvideConverterFactory {
    fun converterFactory():Converter.Factory
}