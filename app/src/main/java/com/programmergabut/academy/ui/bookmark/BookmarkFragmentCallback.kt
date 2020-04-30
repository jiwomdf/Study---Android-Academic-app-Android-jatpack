package com.programmergabut.academy.ui.bookmark

import com.programmergabut.academy.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}