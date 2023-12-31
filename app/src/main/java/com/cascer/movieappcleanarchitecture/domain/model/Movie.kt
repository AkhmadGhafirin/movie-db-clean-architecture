package com.cascer.movieappcleanarchitecture.domain.model

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Long,
    val genreIds: List<Int>,
    val genres: List<Genre>,
    val id: Int,
    val originalLanguage: String,
    val homepage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val status: String,
    val tagline: String,
    val revenue: Long,
    val runtime: Int,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val spokenLanguages: List<SpokenLanguage>
)