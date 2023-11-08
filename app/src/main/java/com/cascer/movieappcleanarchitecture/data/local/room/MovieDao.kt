package com.cascer.movieappcleanarchitecture.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cascer.movieappcleanarchitecture.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllFavorite(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(movie: MovieEntity)

    @Query("DELETE FROM movie WHERE id = :id")
    fun deleteFavorite(id: Int)
}