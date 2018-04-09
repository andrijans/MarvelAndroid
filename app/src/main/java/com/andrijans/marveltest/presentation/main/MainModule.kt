package com.andrijans.marveltest.presentation.main

import com.andrijans.marveltest.domain.ILogger
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
@Module
abstract class MainModule {

    @Binds
    abstract fun provideView(activity: MainActivity): MainContract.View

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providePresenter(view: MainContract.View, logger: ILogger, interactor: MainContract.Interactor): MainContract.Presenter = MainPresenterImpl(view, logger, interactor)

        @JvmStatic
        @Provides
        fun provideMainInteractor(interactor: MainInteractor): MainContract.Interactor = interactor
    }

}