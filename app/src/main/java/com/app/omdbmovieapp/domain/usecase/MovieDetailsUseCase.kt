package com.app.omdbmovieapp.domain.usecase

import com.app.omdbmovieapp.data.repository.MyRepository
import com.app.omdbmovieapp.domain.model.MovieDetailsResponseDto
import com.app.omdbmovieapp.domain.model.MovieResponseDto
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val repository: MyRepository) {
    suspend operator fun invoke(movieId: String, apiKey: String): MovieDetailsResponseDto {
        val response = repository.getMovieDetails(movieId, apiKey)
        return response
    }
}