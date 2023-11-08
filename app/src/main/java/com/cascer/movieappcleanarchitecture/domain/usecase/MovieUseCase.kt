package com.cascer.movieappcleanarchitecture.domain.usecase

import androidx.paging.PagingData
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getListNowPlayingMovie(): Flow<PagingData<Movie>>
    fun getListPopularMovie(): Flow<PagingData<Movie>>
    fun getListTopRatedMovie(): Flow<PagingData<Movie>>
    fun getListUpcomingMovie(): Flow<PagingData<Movie>>
}