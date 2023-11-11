package com.cascer.movieappcleanarchitecture.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    fun detailMovie(id: Int) = movieUseCase.getDetailMovie(id).asLiveData()
    fun castMovie(id: Int) = movieUseCase.getListCastMovie(id).asLiveData()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun statusFavorite(id: Int) = movieUseCase.getStatusFavoriteMovie(id).asLiveData()
    fun videoMovie(id: Int) =
        movieUseCase.getListVideoMovie(id).asLiveData()

    fun addFavorite(movie: Movie, newState: Boolean) =
        movieUseCase.addFavoriteMovie(movie, newState)
}