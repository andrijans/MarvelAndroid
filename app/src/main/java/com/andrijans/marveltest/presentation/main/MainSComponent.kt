package com.andrijans.marveltest.presentation.main

import com.andrijans.marveltest.presentation.common.di.ViewScope
import dagger.Subcomponent

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
@ViewScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainSComponent {
    fun inject(activity: MainActivity): MainActivity

}