package com.example.testapp.di

import com.example.data.cloud.api.CoursesApi
import com.example.data.cloud.provide.MakeService
import com.example.data.cloud.provide.MakeServiceImpl
import com.example.data.cloud.provide.ProvideConverterFactory
import com.example.data.cloud.provide.ProvideConverterFactoryImpl
import com.example.data.cloud.provide.ProvideInterceptor
import com.example.data.cloud.provide.ProvideInterceptorImpl
import com.example.data.cloud.provide.ProvideOkHttpClientBuilder
import com.example.data.cloud.provide.ProvideOkHttpClientBuilderImpl
import com.example.data.cloud.provide.ProvideRetrofitBuilder
import com.example.data.cloud.provide.ProvideRetrofitBuilderImpl
import com.example.data.cloud.source.CoursesDataSource
import com.example.data.cloud.source.CoursesDataSourceImpl
import com.example.data.helper.DispatchersProvider
import org.koin.dsl.module

val dataModule = module {

//    Provide
    single<MakeService> {
        MakeServiceImpl(retrofitBuilder = get())
    }

    single<ProvideConverterFactory> {
        ProvideConverterFactoryImpl()
    }

    single<ProvideInterceptor> {
        ProvideInterceptorImpl.Debug()
    }

    single<ProvideOkHttpClientBuilder> {
        ProvideOkHttpClientBuilderImpl(provideInterceptor = get())
    }

    single<ProvideRetrofitBuilder> {
        ProvideRetrofitBuilderImpl(
            provideConverterFactory = get(),
            provideOkHttpClientBuilder = get()
        )
    }

//    Source
    single<CoursesDataSource> {
        CoursesDataSourceImpl(
            api = get(),
            dispatchersProvider = get()
        )
    }

//    Api
    single<CoursesApi> {
        MakeServiceImpl(retrofitBuilder = get()).service(CoursesApi::class.java)
    }

//    DispatcherProvider
    single<DispatchersProvider> {
        DispatchersProvider.Base()
    }
}