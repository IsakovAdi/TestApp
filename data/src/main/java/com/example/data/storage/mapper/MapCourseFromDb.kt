package com.example.data.storage.mapper

import com.example.data.storage.dto.CourseDb
import com.example.domain.Mapper
import com.example.domain.entity.CourseEntity

class MapCourseFromDb : Mapper<CourseDb, CourseEntity> {
    override fun map(from: CourseDb) = from.run {
        CourseEntity(
            id = id,
            title = title,
            description = description,
            price = price,
            rate = rate,
            startDate = startDate,
            hasLike = hasLike,
            publishDate = publishDate,
        )
    }
}