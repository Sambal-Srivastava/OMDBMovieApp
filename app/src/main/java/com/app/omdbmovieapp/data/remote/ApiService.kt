package com.app.omdbmovieapp.data.remote

import com.app.omdbmovieapp.domain.model.MovieDetailsResponseDto
import com.app.omdbmovieapp.domain.model.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    suspend fun getMovies(@Query("apikey") apiKey:String,
                          @Query("s") searchQuery:String): MovieResponseDto.MockResponse

    @GET(".")
    suspend fun getMovieDetails(@Query("i") movieId:String,
                          @Query("apikey") apiKey:String): MovieDetailsResponseDto
}