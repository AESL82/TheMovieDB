package com.ve.tecno.themoviedb.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ve.tecno.themoviedb.common.Resource
import com.ve.tecno.themoviedb.repository.TheMovieDBRepository
import com.ve.tecno.themoviedb.response.APIError
import com.ve.tecno.themoviedb.response.PopularMoviesResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel: ViewModel() {

    private var theMovieDBRepository: TheMovieDBRepository = TheMovieDBRepository()
    var popularMovies: MutableLiveData<Resource<PopularMoviesResponse>> = MutableLiveData()

    init {
        getPopularesMovies()
    }

    fun getPopularesMovies() = viewModelScope.launch {
        popularMovies.value = Resource.Loading()
        delay(2000)
        val response = theMovieDBRepository.getPopularMovies()
        popularMovies.value = handlePopularMoviesResponse(response)
    }

    private fun handlePopularMoviesResponse(response: Response<PopularMoviesResponse>): Resource<PopularMoviesResponse>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        val error: APIError = theMovieDBRepository.parseError(response)
        return Resource.Error(error.status_message)
    }
}
