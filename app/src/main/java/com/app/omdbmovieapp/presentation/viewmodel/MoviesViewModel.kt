package com.app.omdbmovieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.omdbmovieapp.domain.model.MovieDetailsResponseDto
import com.app.omdbmovieapp.domain.model.MovieEntity
import com.app.omdbmovieapp.domain.model.MovieResponseDto
import com.app.omdbmovieapp.domain.usecase.FetchMoviesUseCase
import com.app.omdbmovieapp.domain.usecase.GetLocalMovieByIdUseCase
import com.app.omdbmovieapp.domain.usecase.GetLocalMoviesUseCase
import com.app.omdbmovieapp.domain.usecase.MovieDetailsUseCase
import com.app.omdbmovieapp.domain.usecase.RegisterMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val registerMovieUseCase: RegisterMovieUseCase,
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
    private val getLocalMovieByIdUseCase: GetLocalMovieByIdUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<MovieResponseDto.MockResponse?>(null)
    val movies: StateFlow<MovieResponseDto.MockResponse?> = _movies

    private val _movieDetails = MutableStateFlow<MovieDetailsResponseDto?>(null)
    val movieDetails: StateFlow<MovieDetailsResponseDto?> = _movieDetails

    private val _localMovies = MutableStateFlow<List<MovieEntity>>(emptyList())
    val localMovies: StateFlow<List<MovieEntity>> = _localMovies

    private val _localMovieDetail = MutableStateFlow<MovieEntity?>(null)
    val localMovieDetail: StateFlow<MovieEntity?> = _localMovieDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchMovies(apiKey: String, searchQuery: String) {
        viewModelScope.launch {
            _isLoading.value = true  // Show progress bar
            try {
                _movies.value = fetchMoviesUseCase(apiKey, searchQuery)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false  // Hide progress bar
            }
        }
    }

    fun fetchMovieDetails(movieId: String, apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true  // Show progress bar
            try {
                _movieDetails.value = movieDetailsUseCase(movieId, apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false  // Hide progress bar
            }
        }
    }

    //local data work
    fun fetchLocalMovies() {
        viewModelScope.launch { _localMovies.value = getLocalMoviesUseCase() }
    }

    fun fetchLocalMovieDetail(id: String) {
        viewModelScope.launch {
            val numericId = id.toIntOrNull()
            _localMovieDetail.value =
                if (numericId != null) getLocalMovieByIdUseCase(numericId) else null
        }
    }

    fun registerMovie(
        title: String,
        director: String,
        genre: String,
        year: String,
        rating: String
    ) {
        viewModelScope.launch {
            val movie = MovieEntity(
                title = title, director = director, genre = genre,
                year = year.toInt(), imdbRating = rating.toFloat()
            )
            registerMovieUseCase(movie)
            fetchLocalMovies()
        }
    }
}