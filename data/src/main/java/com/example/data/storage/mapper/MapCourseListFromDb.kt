package com.example.data.storage.mapper

import com.example.data.storage.dto.CourseDb
import com.example.domain.Mapper
import com.example.domain.entity.CourseEntity

class MapCourseListFromDb(
    private val mapper: Mapper<CourseDb, CourseEntity>,
) : Mapper<List<CourseDb>, List<CourseEntity>> {
    override fun map(from: List<CourseDb>) = from.run {
        map(mapper::map)
    }
}