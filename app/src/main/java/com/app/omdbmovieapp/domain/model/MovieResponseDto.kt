package com.app.omdbmovieapp.domain.model

import com.google.gson.annotations.SerializedName

object MovieResponseDto {

    data class MockResponse(
        @SerializedName("Search") var data: List<MockData>? = null
    )

    data class MockData(
        @SerializedName("Title") var title: String? = null,
        @SerializedName("Year") var year: String? = null,
        @SerializedName("imdbID") var imdbId: String? = null,
        @SerializedName("Type") var type: String? = null,
        @SerializedName("Poster") var poster: String? = null
    )
}
