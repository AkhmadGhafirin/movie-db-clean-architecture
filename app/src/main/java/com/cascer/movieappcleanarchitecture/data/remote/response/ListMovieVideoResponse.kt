package com.cascer.movieappcleanarchitecture.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieVideoResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("results")
    val results: List<MovieVideoResponse>?,
)
