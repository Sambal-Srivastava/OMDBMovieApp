package com.app.omdbmovieapp.di

import com.app.omdbmovieapp.data.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDogRepository(): MyRepository {
        return MyRepository()
    }
}