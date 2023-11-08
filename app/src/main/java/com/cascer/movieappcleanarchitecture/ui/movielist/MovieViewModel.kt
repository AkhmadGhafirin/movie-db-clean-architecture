package com.cascer.movieappcleanarchitecture.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cascer.movieappcleanarchitecture.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    movieUseCase: MovieUseCase
) : ViewModel() {
    val nowPlayingMovies =
        movieUseCase.getListNowPlayingMovie().cachedIn(viewModelScope).asLiveData()
    val popularMovies =
        movieUseCase.getListPopularMovie().cachedIn(viewModelScope).asLiveData()
    val topRatedMovies =
        movieUseCase.getListTopRatedMovie().cachedIn(viewModelScope).asLiveData()
    val upcomingMovies =
        movieUseCase.getListUpcomingMovie().cachedIn(viewModelScope).asLiveData()
    val favoriteMovies =
        movieUseCase.getListFavoriteMovie().asLiveData()
}