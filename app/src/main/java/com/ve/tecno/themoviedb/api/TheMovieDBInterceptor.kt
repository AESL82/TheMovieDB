package com.ve.tecno.themoviedb.api

import com.ve.tecno.themoviedb.common.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TheMovieDBInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        // Añadimos parámetros a la URL de la cadena que recibimos (chain)
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constants.URL_PARAM_API_KEY, Constants.API_KEY)
            .addQueryParameter(Constants.URL_PARAM_LANGUAGE, "es-ES")
            .build()

        var request = chain.request()

        request = request.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)

    }
}