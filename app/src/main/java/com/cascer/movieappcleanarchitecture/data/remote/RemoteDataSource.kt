package com.cascer.movieappcleanarchitecture.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cascer.movieappcleanarchitecture.data.remote.network.ApiResponse
import com.cascer.movieappcleanarchitecture.data.remote.network.ApiService
import com.cascer.movieappcleanarchitecture.data.remote.response.MovieCastResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.MovieResponse
import com.cascer.movieappcleanarchitecture.data.remote.response.MovieVideoResponse
import com.cascer.movieappcleanarchitecture.domain.model.Movie
import com.cascer.movieappcleanarchitecture.domain.model.MovieReview
import com.cascer.movieappcleanarchitecture.utils.DataMapper.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getDetailMovie(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListCastMovie(id: Int): Flow<ApiResponse<List<MovieCastResponse>>> {
        return flow {
            try {
                val response = apiService.getListCastMovie(id)
                val dataArray = response.cast
                if (dataArray?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListVideoMovie(id: Int): Flow<ApiResponse<List<MovieVideoResponse>>> {
        return flow {
            try {
                val response = apiService.getListVideoMovie(id, language = LANGUAGE)
                val dataArray = response.results
                if (dataArray?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getListReviewMovie(id: Int): PagingSource<Int, MovieReview> {
        return object : PagingSource<Int, MovieReview>() {
            override fun getRefreshKey(state: PagingState<Int, MovieReview>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieReview> {
                return try {
                    val position = params.key ?: INITIAL_PAGE_INDEX
                    val responseData = apiService.getListReviewMovie(
                        movieId = id, language = LANGUAGE, page = position
                    )

                    LoadResult.Page(data = responseData.results?.map { it.toDomain() } ?: listOf(),
                        prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                        nextKey = if (responseData.results.isNullOrEmpty()) null else position + 1)
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }
        }
    }

    fun getListNowPlayingMovie(): PagingSource<Int, Movie> {
        return object : PagingSource<Int, Movie>() {
            override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
                return try {
                    val position = params.key ?: INITIAL_PAGE_INDEX
                    val responseData = apiService.getListNowPlayingMovie(
                        language = LANGUAGE, page = position, sortBy = SORT_BY
                    )

                    LoadResult.Page(data = responseData.results?.map { it.toDomain() } ?: listOf(),
                        prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                        nextKey = if (responseData.results.isNullOrEmpty()) null else position + 1)
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }
        }
    }

    fun getListPopularMovie(): PagingSource<Int, Movie> {
        return object : PagingSource<Int, Movie>() {
            override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
                return try {
                    val position = params.key ?: INITIAL_PAGE_INDEX
                    val responseData = apiService.getListPopularMovie(
                        language = LANGUAGE, page = position, sortBy = SORT_BY
                    )

                    LoadResult.Page(data = responseData.results?.map { it.toDomain() } ?: listOf(),
                        prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                        nextKey = if (responseData.results.isNullOrEmpty()) null else position + 1)
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }
        }
    }

    fun getListTopRatedMovie(): PagingSource<Int, Movie> {
        return object : PagingSource<Int, Movie>() {
            override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
                return try {
                    val position = params.key ?: INITIAL_PAGE_INDEX
                    val responseData = apiService.getListTopRatedMovie(
                        language = LANGUAGE, page = position, sortBy = SORT_BY
                    )

                    LoadResult.Page(data = responseData.results?.map { it.toDomain() } ?: listOf(),
                        prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                        nextKey = if (responseData.results.isNullOrEmpty()) null else position + 1)
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }
        }
    }

    fun getListUpcomingMovie(): PagingSource<Int, Movie> {
        return object : PagingSource<Int, Movie>() {
            override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
                return try {
                    val position = params.key ?: INITIAL_PAGE_INDEX
                    val responseData = apiService.getListUpcomingMovie(
                        language = LANGUAGE, page = position, sortBy = SORT_BY
                    )

                    LoadResult.Page(data = responseData.results?.map { it.toDomain() } ?: listOf(),
                        prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                        nextKey = if (responseData.results.isNullOrEmpty()) null else position + 1)
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val LANGUAGE = "en-US"
        const val SORT_BY = "popularity.desc"
    }
}