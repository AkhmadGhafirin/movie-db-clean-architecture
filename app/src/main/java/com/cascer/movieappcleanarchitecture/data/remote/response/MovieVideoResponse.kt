package com.cascer.movieappcleanarchitecture.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieVideoResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("site")
    val site: String?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("official")
    val official: Boolean?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("id")
    val id: String?
)