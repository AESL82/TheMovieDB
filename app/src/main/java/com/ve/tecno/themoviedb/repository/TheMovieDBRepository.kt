package com.ve.tecno.themoviedb.repository

import com.ve.tecno.themoviedb.api.TheMovieDBService
import com.ve.tecno.themoviedb.common.MyApp
import com.ve.tecno.themoviedb.response.APIError
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject



class TheMovieDBRepository @Inject constructor(private val theMovieDBService: TheMovieDBService) {

    suspend fun getPopularMovies() = theMovieDBService.getPopularMovies()

    suspend fun getPopularPeople() = theMovieDBService.getPopularPeople()

    suspend fun getPersonDetail(idPerson: Int) = theMovieDBService.getPersonDetail(idPerson)

    fun parseError(response: Response<*>): APIError {
        val jsonObject = JSONObject(response.errorBody()!!.string())
        return APIError(jsonObject.getInt("status_code"), jsonObject.getString("status_message"))
    }

}