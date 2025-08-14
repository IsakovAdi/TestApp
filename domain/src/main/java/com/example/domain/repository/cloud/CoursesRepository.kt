package com.example.domain.repository.cloud

import com.example.domain.entity.CourseEntity

interface CoursesRepository {
    suspend fun getCourses(id: String): List<CourseEntity>
}