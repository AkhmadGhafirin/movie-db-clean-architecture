package com.cascer.movieappcleanarchitecture.domain.repository

import androidx.paging.PagingData
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getListNowPlayingMovie(): Flow<PagingData<Movie>>
    fun getListPopularMovie(): Flow<PagingData<Movie>>
    fun getListTopRatedMovie(): Flow<PagingData<Movie>>
    fun getListUpcomingMovie(): Flow<PagingData<Movie>>
}