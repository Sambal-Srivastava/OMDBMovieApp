package com.app.omdbmovieapp.data.repository

import com.app.omdbmovieapp.data.local.MovieDao
import com.app.omdbmovieapp.data.remote.RetrofitClient.apiService
import com.app.omdbmovieapp.domain.model.MovieDetailsResponseDto
import com.app.omdbmovieapp.domain.model.MovieEntity
import com.app.omdbmovieapp.domain.model.MovieResponseDto
import javax.inject.Inject

class MyRepository @Inject constructor(private val movieDao: MovieDao) {

    suspend fun getMovies(apiKey: String, searchQuery: String): MovieResponseDto.MockResponse {
        return apiService.getMovies(apiKey, searchQuery)
    }

    suspend fun getMovieDetails(
        movieId: String,
        apiKey: String
    ): MovieDetailsResponseDto {
        return apiService.getMovieDetails(movieId, apiKey)
    }

    suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    suspend fun getLocalMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    suspend fun getLocalMovieById(id: Int): MovieEntity? {
        return movieDao.getMovieById(id)
    }
}