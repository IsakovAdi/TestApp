package com.example.testapp.di

import com.example.domain.use_cases.GetCoursesUseCase
import com.example.domain.use_cases.GetCoursesUseCaseImpl
import com.example.domain.use_cases.StorageCoursesUseCase
import com.example.domain.use_cases.StorageCoursesUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetCoursesUseCase> {
        GetCoursesUseCaseImpl(repository = get())
    }

    factory<StorageCoursesUseCase> {
        StorageCoursesUseCaseImpl(repository = get())
    }
}