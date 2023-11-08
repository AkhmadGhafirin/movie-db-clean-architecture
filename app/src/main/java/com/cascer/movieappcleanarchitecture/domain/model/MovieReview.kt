package com.cascer.movieappcleanarchitecture.domain.model

data class MovieReview(
    val author: String,
    val authorDetails: AuthorDetail,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
)
