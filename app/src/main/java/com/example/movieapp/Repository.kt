package com.example.movieapp

import com.example.local.Dao
import com.example.local.MovieEntity
import com.example.movieapp.bottomNav.accountFragment.SettingData
import com.example.network.API
import com.example.network.MovieResponse
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.State
import com.example.movieapp.utils.wrapWithFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository(private val dataBaseDao: Dao) {

    private var apiServices = API.apiServices

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

    suspend fun addDataToDataBase(item: MovieEntity) {
        withContext(Dispatchers.IO) {
            dataBaseDao.insertMovie(item)
        }
    }

    suspend fun getMoviesListFromDataBase(): State<List<MovieEntity>> {
        val result = dataBaseDao.getMovieFromRoom()
        return if (result.isNotEmpty())
            State.Success(result)
        else
            State.Error("there is no data")
    }

    suspend fun searchQuery(string: String): State<List<MovieEntity>> {
        val result=dataBaseDao.searchQuery(string)
        return if (result.isNotEmpty())
            State.Success(result)
        else
            State.Error("no items found")
    }

    suspend fun deleteItemFromMoviesList(id: Int) {
        dataBaseDao.deleteById(id)
    }

    fun getSettingDataItem(): State<List<SettingData>> {
        return State.Success(Constants.SETTING_DATA_LIST_ITEM)
    }
}


