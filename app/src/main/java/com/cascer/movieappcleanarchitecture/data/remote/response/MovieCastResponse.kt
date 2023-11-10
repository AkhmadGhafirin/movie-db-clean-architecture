package com.cascer.movieappcleanarchitecture.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieCastResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("character")
    val character: String?
)
