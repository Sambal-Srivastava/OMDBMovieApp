package com.app.omdbmovieapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val director: String,
    val genre: String,
    val year: Int,
    val imdbRating: Float,
    val status: String = "Created"
)