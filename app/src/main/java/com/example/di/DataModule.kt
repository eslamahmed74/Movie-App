package com.example.di

import android.app.Application
import android.content.Context
import androidx.core.app.ActivityCompat
import com.example.local.Dao
import com.example.local.MovieListDataBase
import com.example.movieapp.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideDataBase(context: Application): MovieListDataBase {
        return MovieListDataBase.createRoomInstance(context)
    }

    @Provides
    @Singleton
    fun provideDao(movieListDataBase: MovieListDataBase):Dao{
        return movieListDataBase.movieDao()
    }

    @Provides
    fun provideRepo(dao: Dao):Repository{
        return Repository(dao)
    }
}