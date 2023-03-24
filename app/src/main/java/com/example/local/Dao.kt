package com.example.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import retrofit2.Response

@Dao
interface Dao {
    @Insert
    fun insertMovie(movieEntity: MovieEntity)

    @Query("select * from myMoviesList")
    fun getMovieFromRoom(): LiveData<List<MovieEntity>>
}