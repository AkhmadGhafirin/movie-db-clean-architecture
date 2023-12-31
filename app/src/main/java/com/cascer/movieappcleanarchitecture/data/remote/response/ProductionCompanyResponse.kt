package com.cascer.movieappcleanarchitecture.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProductionCompanyResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("origin_country")
    val originCountry: String?
)
