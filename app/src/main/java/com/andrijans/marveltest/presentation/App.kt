package com.andrijans.marveltest.presentation

import android.app.Application
import com.andrijans.marveltest.presentation.common.di.AppComponent
import com.andrijans.marveltest.presentation.common.di.AppModule
import com.andrijans.marveltest.presentation.common.di.DaggerAppComponent

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class App : Application() {
    companion object {
        lateinit var app: App
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        app=this
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}