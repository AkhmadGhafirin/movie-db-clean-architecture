package com.cascer.movieappcleanarchitecture.domain.usecase

import androidx.paging.PagingData
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {
    override fun getListNowPlayingMovie(): Flow<PagingData<Movie>> =
        movieRepository.getListNowPlayingMovie()

    override fun getListPopularMovie(): Flow<PagingData<Movie>> =
        movieRepository.getListPopularMovie()

    override fun getListTopRatedMovie(): Flow<PagingData<Movie>> =
        movieRepository.getListTopRatedMovie()

    override fun getListUpcomingMovie(): Flow<PagingData<Movie>> =
        movieRepository.getListUpcomingMovie()
}