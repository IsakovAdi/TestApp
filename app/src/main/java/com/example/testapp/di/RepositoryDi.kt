package com.example.testapp.di

import com.example.data.cloud.mappers.MapCourse
import com.example.data.cloud.mappers.MapCoursesList
import com.example.data.repositoryImpl.CoursesRepositoryImpl
import com.example.data.repositoryImpl.StorageCourseRepositoryImpl
import com.example.domain.repository.cloud.CoursesRepository
import com.example.domain.repository.storage.StorageRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<CoursesRepository> {
        CoursesRepositoryImpl(mapper = MapCoursesList(courseMapper = MapCourse()), source = get())
    }
    single<StorageRepository> {
        StorageCourseRepositoryImpl(source = get())
    }
}