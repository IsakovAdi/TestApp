package com.example.data.cloud.source

import com.example.data.cloud.dto.CoursesListDto

interface CoursesDataSource {
    suspend fun getCourses(id:String): CoursesListDto
}