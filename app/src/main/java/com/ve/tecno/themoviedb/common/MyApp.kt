package com.ve.tecno.themoviedb.common

import android.app.Application
import com.ve.tecno.themoviedb.api.NetworkContainer

class MyApp: Application() {

    companion object {
        lateinit var instance: MyApp
        val networkContainer = NetworkContainer()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}