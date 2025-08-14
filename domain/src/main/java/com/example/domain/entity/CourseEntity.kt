package com.example.domain.entity


class CourseEntity(
    val id:Int,
    val title:String,
    val description:String,
    val price:String,
    val rate:Double,
    val startDate:String,
    var hasLike:Boolean,
    val publishDate:String
)