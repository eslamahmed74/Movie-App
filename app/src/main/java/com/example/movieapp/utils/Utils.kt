package com.example.movieapp.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> wrapWithFlow(function: suspend (String) -> Response<T>): Flow<State<T?>> {
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

