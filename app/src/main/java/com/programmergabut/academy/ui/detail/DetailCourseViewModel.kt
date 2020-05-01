package com.programmergabut.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.programmergabut.academy.data.source.AcademyRepository
import com.programmergabut.academy.data.source.local.entity.CourseEntity
import com.programmergabut.academy.data.source.local.entity.ModuleEntity
import com.programmergabut.academy.utils.DataDummy

class DetailCourseViewModel(private val academyRepository: AcademyRepository): ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)

    fun getModules(): MutableLiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)

}