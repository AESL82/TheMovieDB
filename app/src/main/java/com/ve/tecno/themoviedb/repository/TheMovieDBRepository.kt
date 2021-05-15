package com.ve.tecno.themoviedb.repository

import com.ve.tecno.themoviedb.common.MyApp
import com.ve.tecno.themoviedb.response.APIError
import org.json.JSONObject
import retrofit2.Response


class TheMovieDBRepository {

    private val theMovieDBService   = MyApp.networkContainer.theMovieDBService
    suspend fun getPopularMovies()  = theMovieDBService.getPopularMovies()

    fun parseError(response: Response<*>): APIError {
        val jsonObject = JSONObject(response.errorBody()?.string())
        return APIError(
                jsonObject.getInt("status_code"),
                jsonObject.getString("status_message"),
                jsonObject.getBoolean("success")
        )
    }
}
