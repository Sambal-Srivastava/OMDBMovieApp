package com.app.omdbmovieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.omdbmovieapp.domain.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}