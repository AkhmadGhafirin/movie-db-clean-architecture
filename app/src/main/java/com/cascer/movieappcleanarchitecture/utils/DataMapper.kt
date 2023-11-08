package com.cascer.movieappcleanarchitecture.utils

import com.cascer.movieappcleanarchitecture.data.remote.response.GenreResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.MovieResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.ProductionCompanyResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.ProductionCountryResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.SpokenLanguageResponse
import com.cascer.movieappcleanarchitecture.domain.model.Genre
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.model.ProductionCompany
import com.cascer.movieappcleanarchitecture.domain.model.ProductionCountry
import com.cascer.movieappcleanarchitecture.domain.model.SpokenLanguage

object DataMapper {
    fun MovieResponse.toDomain() = Movie(
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        budget = budget ?: 0,
        genreIds = genreIds ?: listOf(),
        genres = genres?.map { it.toDomain() } ?: listOf(),
        id = id ?: 0,
        originalLanguage = originalLanguage.orEmpty(),
        homepage = homepage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: 0.0,
        posterPath = posterPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        title = title.orEmpty(),
        status = status.orEmpty(),
        tagline = tagline.orEmpty(),
        revenue = revenue ?: 0,
        runtime = runtime ?: 0,
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        productionCompanies = productionCompanies?.map { it.toDomain() } ?: listOf(),
        productionCountries = productionCountries?.map { it.toDomain() } ?: listOf(),
        spokenLanguages = spokenLanguages?.map { it.toDomain() } ?: listOf(),
    )

    private fun GenreResponse.toDomain() = Genre(
        id = id ?: 0,
        name = name.orEmpty()
    )

    private fun ProductionCompanyResponse.toDomain() = ProductionCompany(
        id = id ?: 0,
        name = name.orEmpty(),
        logoPath = logoPath.orEmpty(),
        originCountry = originCountry.orEmpty()
    )

    private fun ProductionCountryResponse.toDomain() = ProductionCountry(
        name = name.orEmpty(),
        iso31661 = iso31661.orEmpty(),
    )

    private fun SpokenLanguageResponse.toDomain() = SpokenLanguage(
        name = name.orEmpty(),
        englishName = englishName.orEmpty(),
        iso6391 = iso6391.orEmpty()
    )
}