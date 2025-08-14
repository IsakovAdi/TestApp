package com.example.data.cloud.mappers

import com.example.data.CloudDateFormat
import com.example.data.DateFormat
import com.example.data.cloud.dto.CourseDto
import com.example.domain.Mapper
import com.example.domain.entity.CourseEntity
import java.util.Date

class MapCourse : Mapper<CourseDto, CourseEntity> {
    override fun map(from: CourseDto) = from.run {
        CourseEntity(
            id = id,
            title = title,
            description = description,
            price = price,
            rate = rate,
            startDate = startDate,
            hasLike = hasLike,
            publishDate = parseDate(publishDate)
        )
    }

    private fun parseDate(cloudDate:String):String{
        val date = try {
            CloudDateFormat.parse(cloudDate)
        }catch (e:Exception){
            Date()
        }
        return DateFormat.format(date)
    }
}