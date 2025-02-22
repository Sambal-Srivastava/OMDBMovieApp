package com.app.omdbmovieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.omdbmovieapp.domain.model.MovieResponseDto
import com.app.omdbmovieapp.domain.usecase.FetchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<MovieResponseDto.MockResponse?>(null)
    val movies: StateFlow<MovieResponseDto.MockResponse?> = _movies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchMovies(apiKey:String, searchQuery:String) {
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
}