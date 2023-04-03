package com.example.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import retrofit2.Response
import java.util.concurrent.Flow

@Dao
interface Dao {
    @Insert
    fun insertMovie(movieEntity: MovieEntity)

    @Query("select * from myMoviesList order by id desc")
    suspend fun getMovieFromRoom(): List<MovieEntity>

    @Query("delete from myMoviesList where id = :id")
    suspend fun deleteById(id: Int)

    @Query("select * from myMoviesList where name like :query")
    suspend fun searchQuery(query:String):List<MovieEntity>
}