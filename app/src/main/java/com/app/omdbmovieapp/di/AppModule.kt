package com.app.omdbmovieapp.di

import android.content.Context
import androidx.room.Room
import com.app.omdbmovieapp.data.local.MovieDao
import com.app.omdbmovieapp.data.local.MovieDatabase
import com.app.omdbmovieapp.data.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMovieRepository(movieDao: MovieDao): MyRepository {
        return MyRepository(movieDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }
}