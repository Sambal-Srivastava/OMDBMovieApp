package com.app.omdbmovieapp.domain.usecase

import com.app.omdbmovieapp.data.repository.MyRepository
import com.app.omdbmovieapp.domain.model.MovieEntity
import javax.inject.Inject

class RegisterMovieUseCase @Inject constructor(private val repository: MyRepository) {
    suspend operator fun invoke(movie: MovieEntity) {
        repository.insertMovie(movie)
    }
}