package com.app.omdbmovieapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.omdbmovieapp.BuildConfig
import com.app.omdbmovieapp.domain.model.MovieResponseDto
import com.app.omdbmovieapp.presentation.viewmodel.MoviesViewModel

@Composable
fun MovieListingScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {

    val movies by viewModel.movies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMovies(BuildConfig.API_KEY, "marvel")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            movies?.data?.let { movieList ->
                LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                    items(movieList.size) { index ->
                        val movie = movieList[index]
                        MovieItem(movie = movie, onClick = {
                            movie.imdbId?.let { navController.navigate(Screen.MovieDetails(it)) }
                        })

                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: MovieResponseDto.MockData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = movie.title ?: "Unknown Title",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Year: ${movie.year ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}