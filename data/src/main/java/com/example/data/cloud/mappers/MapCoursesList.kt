package com.example.data.cloud.mappers

import com.example.data.cloud.dto.CourseDto
import com.example.data.cloud.dto.CoursesListDto
import com.example.domain.Mapper
import com.example.domain.entity.CourseEntity

class MapCoursesList(
    private val courseMapper: Mapper<CourseDto, CourseEntity>,
) : Mapper<CoursesListDto, List<CourseEntity>> {
    override fun map(from: CoursesListDto) = from.run {
        coursesList.map { course ->
            courseMapper.map(course)
        }
    }
}