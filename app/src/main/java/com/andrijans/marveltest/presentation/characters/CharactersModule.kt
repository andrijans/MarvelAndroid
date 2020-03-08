package com.andrijans.marveltest.presentation.characters

import com.andrijans.marveltest.domain.IApiService
import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.framework.api.interactor.SubscriptionBag
import com.andrijans.marveltest.presentation.common.util.NetworkUtil
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CharactersModule {
    @Binds
    abstract fun provideView(fragment: CharactersFragment): CharactersContract.View

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providePresenter(view: CharactersContract.View, logger: ILogger, interactor: CharactersContract.Interactor, networkUtil: NetworkUtil): CharactersContract.Presenter = CharactersPresenterImpl(view, logger, interactor, networkUtil)

        @JvmStatic
        @Provides
        fun provideCharactersInteractor(apiService: IApiService, logger: ILogger, subscriptionBag: SubscriptionBag): CharactersContract.Interactor = CharactersInteractor(apiService, logger, subscriptionBag)
    }
}