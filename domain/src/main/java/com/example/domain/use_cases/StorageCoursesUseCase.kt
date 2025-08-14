package com.example.domain.use_cases

import com.example.domain.entity.CourseEntity
import com.example.domain.repository.storage.StorageRepository

interface StorageCoursesUseCase {
    suspend fun saveLikeCourseToStorage(courseEntity: CourseEntity)
    suspend fun getLikeCoursesFromStorage(): List<CourseEntity>
    suspend fun deleteCourseFromStorage(courseId: Int)
}

class StorageCoursesUseCaseImpl(
    private val repository: StorageRepository,
) : StorageCoursesUseCase {
    override suspend fun saveLikeCourseToStorage(courseEntity: CourseEntity) {
        repository.saveLikeCourseToStorage(courseEntity)
    }

    override suspend fun getLikeCoursesFromStorage(): List<CourseEntity> =
        repository.getLikeCoursesFromStorage()

    override suspend fun deleteCourseFromStorage(courseId: Int) {
        repository.deleteCourseFromStorage(courseId = courseId)
    }

}