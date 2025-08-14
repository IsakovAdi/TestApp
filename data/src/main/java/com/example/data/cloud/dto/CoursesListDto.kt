package com.example.data.cloud.dto

import com.google.gson.annotations.SerializedName

class CoursesListDto(
    @SerializedName("courses") val coursesList: List<CourseDto>,
)