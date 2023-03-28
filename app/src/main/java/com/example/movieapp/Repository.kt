package com.example.movieapp

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.local.Dao
import com.example.local.MovieEntity
import com.example.local.MovieListDataBase
import com.example.movieapp.network.API
import com.example.movieapp.network.MovieResponse
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.State
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class Repository(private val dataBaseDao: Dao) {

    private var apiServices = API.apiServices

    // private var dao=MovieListDataBase.createRoomInstance()
    suspend fun getPopularMovie(): Flow<State<MovieResponse?>> {
        return wrapWithFlow(apiServices::getPopularMovies)
    }

    suspend fun getTopRatedMovie(): Flow<State<MovieResponse?>> {
        return wrapWithFlow(apiServices::getTopRatedMovie)
    }

    suspend fun getUpComingMovie(): Flow<State<MovieResponse?>> {
        return wrapWithFlow(apiServices::getUpcomingMovies)
    }

    suspend fun getTvPopular(): Flow<State<MovieResponse?>> {
        return wrapWithFlow(apiServices::getTvPopular)
    }

    suspend fun getMoviesList(): State<List<MovieEntity>> {
        val result=dataBaseDao.getMovieFromRoom()
        return if (result.isNotEmpty())
            State.Success(result)
        else
            State.Error("there is no data")
    }
}

private fun <T> wrapWithFlow(function: suspend (String) -> Response<T>): Flow<State<T?>> {
    return flow {
        emit(State.Loading)
        try {
            val result = function(Constants.API_KEY)
            if (result.isSuccessful) {
                delay(1000)
                emit(State.Success(result.body()))
            } else {
                emit(State.Error(result.message()))
            }
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }
}


