package com.cascer.movieappcleanarchitecture.ui.moviefavorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cascer.movieappcleanarchitecture.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFavoriteViewModel @Inject constructor(
    movieUseCase: MovieUseCase
) : ViewModel() {
    val favoriteMovies = movieUseCase.getListFavoriteMovie().asLiveData()
}