package com.cascer.movieappcleanarchitecture.data.remote.response


import com.google.gson.annotations.SerializedName

data class AuthorDetailResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("avatar_path")
    val avatarPath: String?,
    @SerializedName("rating")
    val rating: Double?
)