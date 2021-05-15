package com.ve.tecno.themoviedb.common

import android.app.Application
import com.ve.tecno.themoviedb.api.NetworkModule
import com.ve.tecno.themoviedb.di.ApplicationComponent
import com.ve.tecno.themoviedb.di.DaggerApplicationComponent

class MyApp: Application() {

   val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
   //val appComponent = DaggerApplicationComponent.create()

    companion object {
        lateinit var instance: MyApp
        val networkContainer = NetworkModule()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}