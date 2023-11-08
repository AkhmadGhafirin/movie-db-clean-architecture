package com.cascer.movieappcleanarchitecture.domain.usecase

import androidx.paging.PagingData
import com.cascer.movieappcleanarchitecture.data.Resource
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.model.MovieReview
import com.cascer.movieappcleanarchitecture.domain.model.MovieVideo
import com.cascer.movieappcleanarchitecture.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {
    override fun getDetailMovie(id: Int): Flow<Resource<Movie>> = movieRepository.getDetailMovie(id)

    override fun getListVideoMovie(id: Int): Flow<PagingData<MovieVideo>> =
        movieRepository.getListVideoMovie(id)

    override fun getListReviewMovie(id: Int): Flow<PagingData<MovieReview>> =
        movieRepository.getListReviewMovie(id)

    override fun getListNowPlayingMovie(): Flow<PagingData<Movie>> =
        movieRepository.getListNowPlayingMovie()

    override fun getListPopularMovie(): Flow<PagingData<Movie>> =
        movieRepository.getListPopularMovie()

    override fun getListTopRatedMovie(): Flow<PagingData<Movie>> =
        movieRepository.getListTopRatedMovie()

    override fun getListUpcomingMovie(): Flow<PagingData<Movie>> =
        movieRepository.getListUpcomingMovie()

    override fun getListFavoriteMovie(): Flow<List<Movie>> = movieRepository.getListFavoriteMovie()

    override fun addFavoriteMovie(movie: Movie) = movieRepository.addFavoriteMovie(movie)

    override fun deleteFavoriteMovie(id: Int) = movieRepository.deleteFavoriteMovie(id)
}