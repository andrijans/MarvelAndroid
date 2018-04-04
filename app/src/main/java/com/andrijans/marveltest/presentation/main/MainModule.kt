package com.andrijans.marveltest.presentation.main

import com.andrijans.marveltest.domain.ILogger
import dagger.Module
import dagger.Provides

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
@Module
class MainModule(val view:MainContract.View) {

    @Provides
    fun provideView():MainContract.View=view

    @Provides
    fun providePresenter(logger:ILogger,interactor: MainContract.Interactor):MainContract.Presenter=MainPresenterImpl(view,logger,interactor)

    @Provides
    fun provideMainInteractor(interactor: MainInteractor):MainContract.Interactor=interactor
}