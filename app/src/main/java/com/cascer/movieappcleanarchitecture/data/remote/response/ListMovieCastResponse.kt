package com.cascer.movieappcleanarchitecture.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieCastResponse(
    @SerializedName("cast") val cast: List<MovieCastResponse>?
)