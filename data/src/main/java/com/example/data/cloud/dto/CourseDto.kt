package com.example.data.cloud.dto

import com.google.gson.annotations.SerializedName

class CourseDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("text") val description: String,
    @SerializedName("price") val price: String,
    @SerializedName("rate") val rate: Double,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("hasLike") val hasLike: Boolean,
    @SerializedName("publishDate") val publishDate: String,
)