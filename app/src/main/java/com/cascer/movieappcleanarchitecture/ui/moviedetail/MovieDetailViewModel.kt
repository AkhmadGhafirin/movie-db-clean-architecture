package com.cascer.movieappcleanarchitecture.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    fun detailMovie(id: Int) = movieUseCase.getDetailMovie(id).asLiveData()
    fun videoMovie(id: Int) =
        movieUseCase.getListVideoMovie(id).cachedIn(viewModelScope).asLiveData()

    fun reviewMovie(id: Int) =
        movieUseCase.getListReviewMovie(id).cachedIn(viewModelScope).asLiveData()

    fun addFavorite(movie: Movie) = movieUseCase.addFavoriteMovie(movie)
    fun deleteFavorite(id: Int) = movieUseCase.deleteFavoriteMovie(id)
}