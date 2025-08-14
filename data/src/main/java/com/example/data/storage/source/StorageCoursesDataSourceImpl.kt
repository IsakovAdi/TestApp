package com.example.data.storage.source

import com.example.data.helper.DispatchersProvider
import com.example.data.storage.db.CoursesDao
import com.example.data.storage.dto.CourseDb
import com.example.domain.Mapper
import com.example.domain.entity.CourseEntity
import kotlinx.coroutines.withContext

class StorageCoursesDataSourceImpl(
    private val dao: CoursesDao,
    private val mapToDb: Mapper<CourseEntity, CourseDb>,
    private val mapCourseListFromDb: Mapper<List<CourseDb>, List<CourseEntity>>,
    private val dispatchersProvider: DispatchersProvider,
) : StorageCoursesDataSource {
    override suspend fun saveCourse(courseEntity: CourseEntity) {
        withContext(dispatchersProvider.io()) {
            dao.saveLikeCourseToStorage(course = mapToDb.map(courseEntity))
        }
    }

    override suspend fun getCourses(): List<CourseEntity> {
        return withContext(dispatchersProvider.io()) {
            mapCourseListFromDb.map(from = dao.getStorageLikeCourses())
        }
    }

    override suspend fun deleteCourseFromStorage(courseId: Int) {
        withContext(dispatchersProvider.io()) {
            dao.deleteCourseFromStorage(courseId = courseId)
        }
    }
}