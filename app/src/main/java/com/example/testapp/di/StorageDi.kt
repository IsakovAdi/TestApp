package com.example.testapp.di

import androidx.room.Room
import com.example.data.storage.StorageUtils
import com.example.data.storage.db.CoursesDatabase
import com.example.data.storage.mapper.MapCourseFromDb
import com.example.data.storage.mapper.MapCourseListFromDb
import com.example.data.storage.mapper.MapCourseToDb
import com.example.data.storage.source.StorageCoursesDataSource
import com.example.data.storage.source.StorageCoursesDataSourceImpl
import org.koin.dsl.module

val storageModule = module {
    single<StorageCoursesDataSource> {
        StorageCoursesDataSourceImpl(
            dao = Room.databaseBuilder(
                context = get(),
                CoursesDatabase::class.java,
                StorageUtils.COURSES_DATABASE_NAME
            ).build().dao(),
            mapToDb = MapCourseToDb(),
            mapCourseListFromDb = MapCourseListFromDb(MapCourseFromDb()),
            dispatchersProvider = get()
        )
    }
}