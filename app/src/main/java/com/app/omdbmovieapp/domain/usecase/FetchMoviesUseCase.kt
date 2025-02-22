package com.app.omdbmovieapp.domain.usecase

import com.app.omdbmovieapp.data.repository.MyRepository
import com.app.omdbmovieapp.domain.model.MovieResponseDto
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(private val repository: MyRepository) {
    suspend operator fun invoke(apiKey: String, searchQuery: String): MovieResponseDto.MockResponse {
        val response = repository.getMovies(apiKey, searchQuery)
        return response
    }
}