package com.programmergabut.academy.di

import android.content.Context
import com.programmergabut.academy.data.source.AcademyRepository
import com.programmergabut.academy.data.source.remote.RemoteDataSource
import com.programmergabut.academy.utils.JsonHelper

object Injection {

    fun provideRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }

}