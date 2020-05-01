package com.programmergabut.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.programmergabut.academy.data.source.AcademyRepository
import com.programmergabut.academy.data.source.local.entity.CourseEntity
import com.programmergabut.academy.data.source.local.entity.ModuleEntity
import com.programmergabut.academy.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailCourseViewModelTest{

    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var courseObserver: Observer<CourseEntity>

    @Mock
    private lateinit var modulesObserver: Observer<List<ModuleEntity>>

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        val course = MutableLiveData<CourseEntity>()
        course.value = dummyCourse

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)

        //viewModel.setSelectedCourse(dummyCourse.courseId)
        val courseEntity = viewModel.getCourse()

        verify(academyRepository).getCourseWithModules(courseId)

        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.value?.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.value?.deadline)
        assertEquals(dummyCourse.description, courseEntity.value?.description)
        assertEquals(dummyCourse.imagePath, courseEntity.value?.imagePath)
        assertEquals(dummyCourse.title, courseEntity.value?.title)

        viewModel.getCourse().observeForever(courseObserver)
        verify(courseObserver).onChanged(dummyCourse)
    }

    @Test
    fun getModules() {
        val module = MutableLiveData<List<ModuleEntity>>()
        module.value = dummyModules

        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(module)

        val moduleEntities = viewModel.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId)

        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.value?.size)

        viewModel.getModules().observeForever(modulesObserver)
        verify(modulesObserver).onChanged(dummyModules)
    }

}