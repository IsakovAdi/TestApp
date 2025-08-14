package com.example.data.cloud.api

import com.example.data.cloud.CloudUtils
import com.example.data.cloud.dto.CoursesListDto
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface CoursesApi {
    @POST(CloudUtils.COURSES)
    suspend fun getCourses(
        @Query("id") id:String
    ):Response<CoursesListDto>
}