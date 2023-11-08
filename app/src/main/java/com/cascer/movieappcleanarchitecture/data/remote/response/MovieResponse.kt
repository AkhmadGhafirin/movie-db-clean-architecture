package com.cascer.movieappcleanarchitecture.data.remote.response


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("budget")
    val budget: Long?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("genres")
    val genres: List<GenreResponse>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("revenue")
    val revenue: Long?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>?,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryResponse>?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>?,
)