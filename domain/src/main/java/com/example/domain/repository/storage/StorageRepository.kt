package com.example.domain.repository.storage

import com.example.domain.entity.CourseEntity

interface StorageRepository {
    suspend fun saveLikeCourseToStorage(course: CourseEntity)
    suspend fun getLikeCoursesFromStorage(): List<CourseEntity>
    suspend fun deleteCourseFromStorage(courseId:Int)
}