package com.ve.tecno.themoviedb.api

import com.ve.tecno.themoviedb.common.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkContainer {

    private val theMovieDBInterceptor = TheMovieDBInterceptor()

    private val okHttpClient: OkHttpClient = with(OkHttpClient.Builder()) {
        addInterceptor(theMovieDBInterceptor)
        build()
    }

    val theMovieDBService: TheMovieDBService = Retrofit.Builder()
            .baseUrl(Constantes.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TheMovieDBService::class.java)
}