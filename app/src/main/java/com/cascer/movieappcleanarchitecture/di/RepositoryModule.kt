package com.cascer.movieappcleanarchitecture.di

import com.cascer.movieappcleanarchitecture.data.MovieRepositoryImpl
import com.cascer.movieappcleanarchitecture.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository
}