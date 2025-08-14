package com.example.data.storage.mapper

import com.example.data.storage.dto.CourseDb
import com.example.domain.Mapper
import com.example.domain.entity.CourseEntity

class MapCourseToDb : Mapper<CourseEntity, CourseDb> {
    override fun map(from: CourseEntity) = from.run {
        CourseDb(
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