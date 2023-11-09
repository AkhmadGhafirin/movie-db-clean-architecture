package com.cascer.movieappcleanarchitecture.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cascer.movieappcleanarchitecture.data.local.LocalDataSource
import com.cascer.movieappcleanarchitecture.data.remote.RemoteDataSource
import com.cascer.movieappcleanarchitecture.data.remote.network.ApiResponse
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.model.MovieReview
import com.cascer.movieappcleanarchitecture.domain.model.MovieVideo
import com.cascer.movieappcleanarchitecture.domain.repository.MovieRepository
import com.cascer.movieappcleanarchitecture.utils.AppExecutors
import com.cascer.movieappcleanarchitecture.utils.DataMapper.emptyMovie
import com.cascer.movieappcleanarchitecture.utils.DataMapper.toDomain
import com.cascer.movieappcleanarchitecture.utils.DataMapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieRepository {
    override fun getDetailMovie(id: Int): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getDetailMovie(id).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data.toDomain()))
            }

            is ApiResponse.Empty -> {
                emit(Resource.Success(emptyMovie()))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getListVideoMovie(id: Int): Flow<Resource<List<MovieVideo>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getListVideoMovie(id).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data.map { it.toDomain() }))
            }

            is ApiResponse.Empty -> {
                emit(Resource.Success(listOf()))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun getListReviewMovie(id: Int): Flow<PagingData<MovieReview>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { remoteDataSource.getListReviewMovie(id) }
        ).flow
    }

    override fun getListNowPlayingMovie(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { remoteDataSource.getListNowPlayingMovie() }
        ).flow
    }

    override fun getListPopularMovie(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { remoteDataSource.getListPopularMovie() }
        ).flow
    }

    override fun getListTopRatedMovie(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { remoteDataSource.getListTopRatedMovie() }
        ).flow
    }

    override fun getListUpcomingMovie(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { remoteDataSource.getListUpcomingMovie() }
        ).flow
    }

    override fun getListFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getAllFavorite().map { it.map { movie -> movie.toDomain() } }
    }

    override fun getStatusFavoriteMovie(id: Int): Flow<Movie> {
        return localDataSource.getStatusFavorite(id).map { it?.toDomain() ?: emptyMovie() }
    }

    override fun addFavoriteMovie(movie: Movie, newState: Boolean) {
        appExecutors.diskIO().execute {
            if (newState) {
                localDataSource.insertFavorite(movie.toEntity())
            } else {
                localDataSource.deleteFavorite(movie.id)
            }
        }
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}