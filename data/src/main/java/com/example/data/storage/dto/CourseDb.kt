package com.example.data.storage.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.storage.StorageUtils.COURSES_TABLE_NAME

@Entity(tableName = COURSES_TABLE_NAME)
class CourseDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rate: Double,
    val startDate: String,
    var hasLike: Boolean,
    val publishDate: String,
)