package com.programmergabut.academy.di

import android.content.Context
import com.programmergabut.academy.data.source.AcademyRepository
import com.programmergabut.academy.data.source.local.LocalDataSource
import com.programmergabut.academy.data.source.local.room.AcademyDatabase
import com.programmergabut.academy.data.source.remote.RemoteDataSource
import com.programmergabut.academy.utils.AppExecutors
import com.programmergabut.academy.utils.JsonHelper

object Injection {

    fun provideRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

}