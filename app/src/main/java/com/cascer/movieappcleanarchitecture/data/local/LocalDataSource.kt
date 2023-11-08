package com.cascer.movieappcleanarchitecture.data.local

import com.cascer.movieappcleanarchitecture.data.local.entity.MovieEntity
import com.cascer.movieappcleanarchitecture.data.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllFavorite(): Flow<List<MovieEntity>> = movieDao.getAllFavorite()

    fun insertFavorite(movie: MovieEntity) = movieDao.insertFavorite(movie)

    fun deleteFavorite(id: Int) = movieDao.deleteFavorite(id)
}