package com.cascer.movieappcleanarchitecture.data.remote.network

import com.cascer.movieappcleanarchitecture.data.remote.response.ListMovieResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.ListMovieReviewResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.ListMovieVideoResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getListPopularMovie(
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
    ): ListMovieResponse

    @GET("discover/movie")
    suspend fun getListNowPlayingMovie(
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
    ): ListMovieResponse

    @GET("movie/top_rated")
    suspend fun getListTopRatedMovie(
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
    ): ListMovieResponse

    @GET("movie/upcoming")
    suspend fun getListUpcomingMovie(
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
    ): ListMovieResponse

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") movieId: Int
    ): MovieResponse

    @GET("movie/{id}/videos")
    suspend fun getListVideoMovie(
        @Path("id") movieId: Int,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): ListMovieVideoResponse

    @GET("movie/{id}/reviews")
    suspend fun getListReviewMovie(
        @Path("id") movieId: Int,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): ListMovieReviewResponse
}