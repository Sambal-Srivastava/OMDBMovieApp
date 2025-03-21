package com.app.omdbmovieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.app.omdbmovieapp.presentation.screens.MovieDetailScreen
import com.app.omdbmovieapp.presentation.screens.MovieListingScreen
import com.app.omdbmovieapp.presentation.screens.MovieRegistrationScreen
import com.app.omdbmovieapp.presentation.screens.Screen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MovieListing) {
        composable<Screen.MovieListing> {
            MovieListingScreen(navController)
        }
        composable<Screen.MovieDetails> {
            val movieDetails: Screen.MovieDetails = it.toRoute()
            MovieDetailScreen(navController, movieDetails.movieId)
        }
        composable<Screen.MovieRegistartion> {
            MovieRegistrationScreen(navController)
        }
    }
}