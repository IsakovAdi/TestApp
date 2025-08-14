package com.example.domain.use_cases

import com.example.domain.entity.CourseEntity
import com.example.domain.repository.cloud.CoursesRepository

interface GetCoursesUseCase {
    suspend operator fun invoke(id: String): List<CourseEntity>
}

class GetCoursesUseCaseImpl(
    private val repository: CoursesRepository,
) : GetCoursesUseCase {
    override suspend fun invoke(id: String): List<CourseEntity> {
        return repository.getCourses(id = id)
    }
}