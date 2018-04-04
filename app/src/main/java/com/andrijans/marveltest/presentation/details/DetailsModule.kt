package com.andrijans.marveltest.presentation.details

import dagger.Module
import dagger.Provides

/**
 * Created by andrijanstankovic on 04/04/2018.
 */
@Module
class DetailsModule(val view: DetailsContract.View) {

    @Provides
    fun provideView(): DetailsContract.View = view

    @Provides
    fun providePresenter(): DetailsContract.Presenter = DetailsPresenterImpl(view)
}