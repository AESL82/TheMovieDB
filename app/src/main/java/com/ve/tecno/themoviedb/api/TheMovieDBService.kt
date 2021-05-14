package com.ve.tecno.themoviedb.api

import com.ve.tecno.themoviedb.response.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface TheMovieDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<PopularMoviesResponse>
}