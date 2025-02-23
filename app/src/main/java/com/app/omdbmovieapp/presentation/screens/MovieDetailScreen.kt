package com.app.omdbmovieapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.app.omdbmovieapp.BuildConfig
import com.app.omdbmovieapp.presentation.viewmodel.MoviesViewModel

@Composable
fun MovieDetailScreen(navController: NavController, moviesId: String) {
    var viewModel: MoviesViewModel = hiltViewModel()
    var isLocal = false
    LaunchedEffect(key1 = moviesId) {
        val numericId = moviesId.toIntOrNull()
        if (numericId != null) {
            isLocal = true
            viewModel.fetchLocalMovieDetail(moviesId)
        } else {
            isLocal = false
            viewModel.fetchMovieDetails(movieId = moviesId, apiKey = BuildConfig.API_KEY)
        }
        moviesId.let {
        }
    }

    //observe movie details
    val movieDetails by viewModel.movieDetails.collectAsState()
    val localMovieDetails by viewModel.localMovieDetail.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 16.dp, end = 16.dp)
    ) {
        if (!isLocal) {
            AsyncImage(
                model = movieDetails?.poster,
                contentDescription = null,
            )
        }
        Text(
            text = if (isLocal) localMovieDetails?.title.toString() else movieDetails?.title.toString()
        )
        Text(
            text = if (isLocal) localMovieDetails?.year.toString() else movieDetails?.year.toString()
        )
    }

}