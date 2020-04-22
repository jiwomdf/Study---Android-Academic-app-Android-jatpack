package com.programmergabut.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.programmergabut.academy.data.CourseEntity
import com.programmergabut.academy.utils.DataDummy

class BookmarkViewModel: ViewModel() {

    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}