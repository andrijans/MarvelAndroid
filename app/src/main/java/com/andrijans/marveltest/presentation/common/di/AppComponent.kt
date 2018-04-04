package com.andrijans.marveltest.presentation.common.di

import com.andrijans.marveltest.framework.api.ApiModule
import com.andrijans.marveltest.framework.api.AuthInterceptor
import com.andrijans.marveltest.presentation.details.DetailsModule
import com.andrijans.marveltest.presentation.details.DetailsSComponent
import com.andrijans.marveltest.presentation.main.MainModule
import com.andrijans.marveltest.presentation.main.MainSComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ApiModule::class))
interface AppComponent {
    fun plus(module: MainModule): MainSComponent
    fun plus(module: DetailsModule): DetailsSComponent

    fun inject(authInterceptor: AuthInterceptor): AuthInterceptor

}