package com.programmergabut.academy.ui.reader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.programmergabut.academy.data.source.AcademyRepository
import com.programmergabut.academy.data.source.local.entity.ContentEntity
import com.programmergabut.academy.data.source.local.entity.ModuleEntity
import com.programmergabut.academy.utils.DataDummy

class CourseReaderViewModel(private val academyRepository: AcademyRepository): ViewModel() {

    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): MutableLiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): MutableLiveData<ModuleEntity> = academyRepository.getContent(courseId, moduleId)



}