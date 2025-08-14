package com.example.data.repositoryImpl

import com.example.data.storage.source.StorageCoursesDataSource
import com.example.domain.entity.CourseEntity
import com.example.domain.repository.storage.StorageRepository

class StorageCourseRepositoryImpl(
    private val source: StorageCoursesDataSource,
) : StorageRepository {
    override suspend fun saveLikeCourseToStorage(course: CourseEntity) {
        source.saveCourse(courseEntity = course)
    }

    override suspend fun getLikeCoursesFromStorage(): List<CourseEntity> = source.getCourses()
    override suspend fun deleteCourseFromStorage(courseId: Int) {
        source.deleteCourseFromStorage(courseId = courseId)
    }
}