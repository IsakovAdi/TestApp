package com.example.data.storage.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.data.storage.dto.CourseDb

@Dao
interface CoursesDao {
    @Query("SELECT * FROM COURSES_TABLE_NAME")
    suspend fun getStorageLikeCourses(): List<CourseDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLikeCourseToStorage(course: CourseDb)

    @Query("DELETE FROM COURSES_TABLE_NAME WHERE id=:courseId")
    suspend fun deleteCourseFromStorage(courseId: Int)
}

@Database(entities = [CourseDb::class], version = 1, exportSchema = false)
abstract class CoursesDatabase : RoomDatabase() {
    abstract fun dao(): CoursesDao
}