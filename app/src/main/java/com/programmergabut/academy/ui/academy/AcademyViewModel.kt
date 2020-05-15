package com.programmergabut.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.programmergabut.academy.data.source.AcademyRepository
import com.programmergabut.academy.data.source.local.entity.CourseEntity
import com.programmergabut.academy.utils.DataDummy
import com.programmergabut.academy.vo.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository): ViewModel() {

    fun getCourses(): LiveData<Resource<List<CourseEntity>>> = academyRepository.getAllCourses()

}