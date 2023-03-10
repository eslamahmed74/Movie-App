package com.example.movieapp

import android.util.Log
import com.example.movieapp.network.API
import com.example.movieapp.network.MovieResponse
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class Repository {

    private var apiServices = API.apiServices

    suspend fun getPopularMovie(): Flow<State<MovieResponse?>> {

        return wrapWithFlow(apiServices::getPopularMovies)
    }

    suspend fun getTopRatedMovie():Flow<State<MovieResponse?>>{

        return wrapWithFlow(apiServices::getTopRatedMovie)
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
}