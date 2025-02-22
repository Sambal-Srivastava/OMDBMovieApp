package com.app.omdbmovieapp.data.repository

import com.app.omdbmovieapp.data.remote.RetrofitClient.apiService
import com.app.omdbmovieapp.domain.model.MovieResponseDto
import javax.inject.Inject

class MyRepository @Inject constructor() {

    suspend fun getMovies(apiKey: String, searchQuery: String): MovieResponseDto.MockResponse {
        return apiService.getMovies(apiKey, searchQuery)
    }

    suspend fun getMovieDetails(
        movieId: String,
        apiKey: String
    ): MovieResponseDto.MovieDetailsResponse {
        return apiService.getMovieDetails(movieId, apiKey)
    }
}