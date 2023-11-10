package com.cascer.movieappcleanarchitecture.utils

import com.cascer.movieappcleanarchitecture.data.local.entity.MovieEntity
import com.cascer.movieappcleanarchitecture.data.remote.response.AuthorDetailResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.GenreResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.MovieCastResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.MovieResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.MovieReviewResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.MovieVideoResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.ProductionCompanyResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.ProductionCountryResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.SpokenLanguageResponse
import com.cascer.movieappcleanarchitecture.domain.model.AuthorDetail
import com.cascer.movieappcleanarchitecture.domain.model.Genre
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.model.MovieCast
import com.cascer.movieappcleanarchitecture.domain.model.MovieReview
import com.cascer.movieappcleanarchitecture.domain.model.MovieVideo
import com.cascer.movieappcleanarchitecture.domain.model.ProductionCompany
import com.cascer.movieappcleanarchitecture.domain.model.ProductionCountry
import com.cascer.movieappcleanarchitecture.domain.model.SpokenLanguage

object DataMapper {

    fun Movie.toEntity() = MovieEntity(
        id = id,
        title = title,
        posterPath = posterPath
    )

    fun MovieEntity.toDomain() = Movie(
        adult = false,
        backdropPath = "",
        budget = 0,
        genreIds = listOf(),
        genres = listOf(),
        id = id,
        originalLanguage = "",
        homepage = "",
        originalTitle = "",
        overview = "",
        popularity = 0.0,
        posterPath = posterPath,
        releaseDate = "",
        title = title,
        status = "",
        tagline = "",
        revenue = 0,
        runtime = 0,
        video = false,
        voteAverage = 0.0,
        voteCount = 0,
        productionCompanies = listOf(),
        productionCountries = listOf(),
        spokenLanguages = listOf(),
    )

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

    fun MovieVideoResponse.toDomain() = MovieVideo(
        name = name.orEmpty(),
        key = key.orEmpty(),
        site = site.orEmpty(),
        size = size ?: 0,
        type = type.orEmpty(),
        official = official ?: false,
        publishedAt = publishedAt.orEmpty(),
        id = id.orEmpty()
    )

    fun MovieCastResponse.toDomain() = MovieCast(
        name = name.orEmpty(),
        profilePath = profilePath.orEmpty(),
        originalName = originalName.orEmpty(),
        character = character.orEmpty(),
        id = id ?: 0
    )

    fun MovieReviewResponse.toDomain() = MovieReview(
        author = author.orEmpty(),
        authorDetails = authorDetails?.toDomain() ?: AuthorDetail("", "", "", 0.0),
        content = content.orEmpty(),
        createdAt = createdAt.orEmpty(),
        id = id.orEmpty(),
        updatedAt = updatedAt.orEmpty(),
        url = url.orEmpty()
    )

    fun AuthorDetailResponse.toDomain() = AuthorDetail(
        name = name.orEmpty(),
        username = username.orEmpty(),
        avatarPath = avatarPath.orEmpty(),
        rating = rating ?: 0.0
    )

    fun emptyMovie() = Movie(
        adult = false,
        backdropPath = "",
        budget = 0,
        genreIds = listOf(),
        genres = listOf(),
        id = 0,
        originalLanguage = "",
        homepage = "",
        originalTitle = "",
        overview = "",
        popularity = 0.0,
        posterPath = "",
        releaseDate = "",
        title = "",
        status = "",
        tagline = "",
        revenue = 0,
        runtime = 0,
        video = false,
        voteAverage = 0.0,
        voteCount = 0,
        productionCompanies = listOf(),
        productionCountries = listOf(),
        spokenLanguages = listOf(),
    )
}