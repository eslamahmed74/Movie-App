package com.example.movieapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse?>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") apiKey: String
    ):Response<MovieResponse?>
}