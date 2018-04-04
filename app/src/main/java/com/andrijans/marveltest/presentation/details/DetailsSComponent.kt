package com.andrijans.marveltest.presentation.details

import com.andrijans.marveltest.presentation.common.di.ViewScope
import dagger.Subcomponent

/**
 * Created by andrijanstankovic on 04/04/2018.
 */
@ViewScope
@Subcomponent(modules = arrayOf(DetailsModule::class))
interface DetailsSComponent {
    fun inject(activity:DetailsActivity):DetailsActivity
}