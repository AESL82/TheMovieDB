package com.ve.tecno.themoviedb.api

import com.ve.tecno.themoviedb.common.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("apiUrl")
    fun provideBaseUrl(): String = Constants.TMDBAPI_BASE_URL

    @Singleton
    @Provides
    @Named("imageUrl")
    fun provideImageBaseUrl(): String = Constants.IMAGE_BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(theMovieDBInterceptor: TheMovieDBInterceptor): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            addInterceptor(theMovieDBInterceptor)
            connectTimeout(Constants.TIMEOUT_INMILIS, TimeUnit.MILLISECONDS)
            build()
        }
    }

    @Singleton
    @Provides
    fun provideTheMovieDBService(@Named("apiUrl") url: String, okHttpClient: OkHttpClient): TheMovieDBService {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TheMovieDBService::class.java)
    }

}