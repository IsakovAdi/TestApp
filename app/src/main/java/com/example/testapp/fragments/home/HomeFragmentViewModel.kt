package com.example.testapp.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.DateFormat
import com.example.domain.entity.CourseEntity
import com.example.domain.use_cases.GetCoursesUseCase
import com.example.domain.use_cases.StorageCoursesUseCase
import kotlinx.coroutines.launch

class HomeFragmentViewModel(
    private val courseUseCase: GetCoursesUseCase,
    private val storageCoursesUseCase: StorageCoursesUseCase,
) : ViewModel() {
    private val _coursesLiveData = MutableLiveData<List<CourseEntity>>()
    val coursesLiveData: LiveData<List<CourseEntity>> get() = _coursesLiveData

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getAllCourses() {
        startProgressing()
        viewModelScope.launch {
            kotlin.runCatching {
                courseUseCase(COURSES_ID)
            }.onSuccess {
                _coursesLiveData.value = it
                it.forEach { course ->
                    if (course.hasLike) {
                        storageCoursesUseCase.saveLikeCourseToStorage(course)
                    }
                }
                stopProgressing()
            }.onFailure {
                _coursesLiveData.value = emptyList()
                stopProgressing()
            }
        }
    }

    fun courseHasLikeFlagClicked(course: CourseEntity) {
        viewModelScope.launch {
            if (course.hasLike) {
                storageCoursesUseCase.saveLikeCourseToStorage(course)
            } else {
                storageCoursesUseCase.deleteCourseFromStorage(courseId = course.id)
            }
        }
    }

    fun sortClicked() {
        val sortedList = _coursesLiveData.value?.toMutableList()?.sortedByDescending {
            startProgressing()
            DateFormat.parse(it.publishDate)
        }
        sortedList?.let {
            stopProgressing()
            _coursesLiveData.value = it
        }
    }

    private fun startProgressing() {
        _isLoading.value = true
    }

    private fun stopProgressing() {
        _isLoading.value = false
    }

    companion object {
        const val COURSES_ID = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q"
    }
}