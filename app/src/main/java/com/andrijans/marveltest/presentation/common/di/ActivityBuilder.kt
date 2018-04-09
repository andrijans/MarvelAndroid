package com.andrijans.marveltest.presentation.common.di

import com.andrijans.marveltest.presentation.details.DetailsActivity
import com.andrijans.marveltest.presentation.details.DetailsModule
import com.andrijans.marveltest.presentation.main.MainActivity
import com.andrijans.marveltest.presentation.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by andrijanstankovic on 06/04/2018.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(DetailsModule::class)])
    abstract fun bindDetailsActivity(): DetailsActivity
}