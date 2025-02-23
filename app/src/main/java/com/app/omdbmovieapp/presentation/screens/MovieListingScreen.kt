package com.app.omdbmovieapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.app.omdbmovieapp.BuildConfig
import com.app.omdbmovieapp.R
import com.app.omdbmovieapp.domain.model.MovieResponseDto
import com.app.omdbmovieapp.presentation.viewmodel.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListingScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()

    //handling and rendering of local data
    val localMovies by viewModel.localMovies.collectAsState()
    val movies by viewModel.movies.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMovies(BuildConfig.API_KEY, "marvel")
        viewModel.fetchLocalMovies()
    }
    val combinedMovies = (localMovies.map { movie ->
        MovieResponseDto.MockData(
            title = movie.title, year = movie.year.toString(),
            imdbId = movie.id.toString(), type = "Created", poster = ""
        )
    } + (movies?.data ?: emptyList())).distinctBy { it.imdbId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            TopAppBar(
                title = { Text("Movie Listing") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.MovieRegistartion) }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_input_add),
                            contentDescription = "Add Movie"
                        )
                    }
                }
            )
            LazyColumn {
                items(combinedMovies) { movie ->
                    MovieItem(movie, onClick = {
                        navController.navigate(Screen.MovieDetails(movie.imdbId ?: ""))
                    })
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
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                if (!movie.poster.isNullOrEmpty()) {
                    AsyncImage(
                        model = movie.poster,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Text(
                    text = movie.title ?: "Unknown Title",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Year: ${movie.year ?: "N/A"}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            if (movie.type == "Created") {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
//                        .offset(y = (10).dp, x = (10).dp)
                        .background(Color.Red, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("Created", color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}