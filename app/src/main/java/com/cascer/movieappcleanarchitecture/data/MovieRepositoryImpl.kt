package com.cascer.movieappcleanarchitecture.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cascer.movieappcleanarchitecture.data.remote.RemoteDataSource
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): MovieRepository {
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

    private companion object {
       const val PAGE_SIZE = 20
    }
}