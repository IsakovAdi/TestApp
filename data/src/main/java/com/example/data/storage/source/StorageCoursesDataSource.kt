package com.example.data.storage.source

import com.example.domain.entity.CourseEntity

interface StorageCoursesDataSource {

    suspend fun saveCourse(courseEntity: CourseEntity)
    suspend fun getCourses(): List<CourseEntity>
    suspend fun deleteCourseFromStorage(courseId: Int)
}