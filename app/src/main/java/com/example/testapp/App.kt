package com.example.testapp

import android.app.Application
import com.example.testapp.di.appModule
import com.example.testapp.di.dataModule
import com.example.testapp.di.domainModule
import com.example.testapp.di.repositoryModule
import com.example.testapp.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    domainModule,
                    repositoryModule,
                    dataModule,
                    storageModule
                )
            )
        }
    }
}