package com.programmergabut.academy.ui.bookmark

import com.programmergabut.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}