package com.programmergabut.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.programmergabut.academy.data.CourseEntity
import com.programmergabut.academy.utils.DataDummy

class AcademyViewModel: ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()



}