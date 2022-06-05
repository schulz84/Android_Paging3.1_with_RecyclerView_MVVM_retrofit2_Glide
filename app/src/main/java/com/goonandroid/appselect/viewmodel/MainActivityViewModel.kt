package com.goonandroid.appselect.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.goonandroid.appselect.Movie
import com.goonandroid.appselect.paging.MoviePagingSource
import com.goonandroid.appselect.servicesapi.MovieApiInterface
import com.goonandroid.appselect.servicesapi.MovieApiService
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel: ViewModel() {
    var retroService: MovieApiInterface = MovieApiService.getInstance().create(MovieApiInterface::class.java)

    fun getListData(): Flow<PagingData<Movie>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { MoviePagingSource(retroService) }).flow.cachedIn(viewModelScope)
    }
}