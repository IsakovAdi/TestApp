package com.example.data.cloud.source

import com.example.data.cloud.api.CoursesApi
import com.example.data.cloud.dto.CoursesListDto
import com.example.data.helper.DispatchersProvider
import kotlinx.coroutines.withContext

class CoursesDataSourceImpl(
    private val api: CoursesApi,
    private val dispatchersProvider: DispatchersProvider,
) : CoursesDataSource {
    override suspend fun getCourses(id: String): CoursesListDto =
        withContext(dispatchersProvider.io()) {
            api.getCourses(id = id).body() ?: CoursesListDto(emptyList())
        }
}