package com.goonandroid.appselect.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.goonandroid.appselect.Movie
import com.goonandroid.appselect.servicesapi.MovieApiInterface

class MoviePagingSource(val apiService: MovieApiInterface): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {

        return state.anchorPosition

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber : Int = params.key ?: FIRST_PAGE_INDEX
            val pageSize = MAX_PAGE_SIZE
            val response = apiService.getMovieList(pageNumber*pageSize)

            val movies = response.movies
            val nextPageNumber = if (movies.isEmpty()) null else pageNumber + 1
            val prevPageNumber = if (pageNumber == 0) null else pageNumber - 1
            return LoadResult.Page(movies, prevPageNumber, nextPageNumber)

        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    companion object {
        private const val FIRST_PAGE_INDEX = 0
        private const val MAX_PAGE_SIZE = 20
    }

}

