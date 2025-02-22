package com.app.omdbmovieapp.presentation.screens

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object MovieListing : Screen()

    @Serializable
    data class MovieDetails(val movieId: String) : Screen()

    @Serializable
    object MovieRegistartion : Screen()

}