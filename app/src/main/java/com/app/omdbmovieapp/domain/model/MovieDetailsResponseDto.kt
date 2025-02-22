package com.app.omdbmovieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponseDto(
    @SerializedName("Title") var title: String? = null,
    @SerializedName("Year") var year: String? = null,
    @SerializedName("imdbID") var imdbId: String? = null,
    @SerializedName("Type") var type: String? = null,
    @SerializedName("Poster") var poster: String? = null
)