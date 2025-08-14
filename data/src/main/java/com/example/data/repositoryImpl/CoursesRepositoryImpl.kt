package com.example.data.repositoryImpl

import com.example.data.cloud.source.CoursesDataSource
import com.example.data.cloud.dto.CoursesListDto
import com.example.domain.Mapper
import com.example.domain.entity.CourseEntity
import com.example.domain.repository.cloud.CoursesRepository

class CoursesRepositoryImpl(
    private val mapper: Mapper<CoursesListDto, List<CourseEntity>>,
    private val source: CoursesDataSource,
) : CoursesRepository {
    override suspend fun getCourses(id: String): List<CourseEntity> {
        return mapper.map(from = source.getCourses(id = id))
    }
}