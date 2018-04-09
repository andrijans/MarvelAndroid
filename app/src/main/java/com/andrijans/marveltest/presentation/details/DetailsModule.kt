package com.andrijans.marveltest.presentation.details

import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by andrijanstankovic on 04/04/2018.
 */
@Module
abstract class DetailsModule {

    @Binds
    abstract fun provideView(activity: DetailsActivity): DetailsContract.View

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providePresenter(view: DetailsContract.View): DetailsContract.Presenter = DetailsPresenterImpl(view)
    }


}