package com.app.omdbmovieapp.domain.usecase

import com.app.omdbmovieapp.data.repository.MyRepository
import com.app.omdbmovieapp.domain.model.MovieEntity
import javax.inject.Inject

class GetLocalMovieByIdUseCase @Inject constructor(private val repository: MyRepository) {
    suspend operator fun invoke(id: Int): MovieEntity? {
        return repository.getLocalMovieById(id)
    }
}