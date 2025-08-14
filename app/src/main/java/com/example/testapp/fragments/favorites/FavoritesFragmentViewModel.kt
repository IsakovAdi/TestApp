package com.example.testapp.fragments.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CourseEntity
import com.example.domain.use_cases.StorageCoursesUseCase
import kotlinx.coroutines.launch

class FavoritesFragmentViewModel(
    private val storageCoursesUseCase: StorageCoursesUseCase,
) : ViewModel() {

    private val _coursesLiveData = MutableLiveData<List<CourseEntity>>()
    val coursesLiveData: LiveData<List<CourseEntity>> get() = _coursesLiveData

    fun getCourses() {
        viewModelScope.launch {
            kotlin.runCatching {
                storageCoursesUseCase.getLikeCoursesFromStorage()
            }.onSuccess {
                _coursesLiveData.value = it.toMutableList()
            }.onFailure {
                _coursesLiveData.value = mutableListOf()
            }
        }
    }


    fun deleteCourse(courseId: Int) {
        val list = _coursesLiveData.value?.toMutableList()
        viewModelScope.launch {
            storageCoursesUseCase.deleteCourseFromStorage(courseId = courseId)
            list?.let {
                it.removeIf { course ->
                    course.id == courseId
                }
            }
        }
        list?.let {
            _coursesLiveData.value = it
        }
    }
}