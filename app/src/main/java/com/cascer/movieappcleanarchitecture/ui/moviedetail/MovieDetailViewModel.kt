package com.cascer.movieappcleanarchitecture.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cascer.movieappcleanarchitecture.data.Resource
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.model.MovieCast
import com.cascer.movieappcleanarchitecture.domain.model.MovieVideo
import com.cascer.movieappcleanarchitecture.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    fun detailMovie(id: Int) = movieUseCase.getDetailMovie(id).asLiveData()
    fun castMovie(id: Int) = movieUseCase.getListCastMovie(id).asLiveData()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _detail = MutableLiveData<Resource<Movie>>()
    val detail: LiveData<Resource<Movie>> = _detail

    private val _cast = MutableLiveData<Resource<MovieCast>>()
    val cast: LiveData<Resource<MovieCast>> = _cast

    private val _video = MutableLiveData<Resource<MovieVideo>>()
    val video: LiveData<Resource<MovieVideo>> = _video
    fun requestDetail(id: Int) {
        viewModelScope.launch {
            val abc = movieUseCase.getDetailMovie(id)
                .catch { _error.postValue(it.message ?: "unknow error") }
            val bcd = movieUseCase.getListCastMovie(id)
                .catch { _error.postValue(it.message ?: "unknow error") }
            val combine = abc.combine(bcd) { a, b ->
                Pair(a, b)
            }.first()
        }
    }

    fun statusFavorite(id: Int) = movieUseCase.getStatusFavoriteMovie(id).asLiveData()
    fun videoMovie(id: Int) =
        movieUseCase.getListVideoMovie(id).asLiveData()

    fun addFavorite(movie: Movie, newState: Boolean) =
        movieUseCase.addFavoriteMovie(movie, newState)
}