package com.cascer.movieappcleanarchitecture.domain.repository

import androidx.paging.PagingData
import com.cascer.movieappcleanarchitecture.data.Resource
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.model.MovieReview
import com.cascer.movieappcleanarchitecture.domain.model.MovieVideo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getDetailMovie(id: Int): Flow<Resource<Movie>>
    fun getListVideoMovie(id: Int): Flow<PagingData<MovieVideo>>
    fun getListReviewMovie(id: Int): Flow<PagingData<MovieReview>>
    fun getListNowPlayingMovie(): Flow<PagingData<Movie>>
    fun getListPopularMovie(): Flow<PagingData<Movie>>
    fun getListTopRatedMovie(): Flow<PagingData<Movie>>
    fun getListUpcomingMovie(): Flow<PagingData<Movie>>
    fun getListFavoriteMovie(): Flow<List<Movie>>
    fun addFavoriteMovie(movie: Movie)
    fun deleteFavoriteMovie(id: Int)
}