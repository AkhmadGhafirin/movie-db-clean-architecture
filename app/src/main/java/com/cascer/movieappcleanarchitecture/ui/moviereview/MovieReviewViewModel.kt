package com.cascer.movieappcleanarchitecture.ui.moviereview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cascer.movieappcleanarchitecture.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieReviewViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    fun reviewMovie(id: Int) =
        movieUseCase.getListReviewMovie(id).cachedIn(viewModelScope).asLiveData()
}