package com.andrijans.marveltest.presentation.common.di

import android.app.Application
import android.content.Context
import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.domain.IResultThread
import com.andrijans.marveltest.domain.IWorkerThread
import com.andrijans.marveltest.framework.api.interactor.SubscriptionBag
import com.andrijans.marveltest.framework.monitoring.Logger
import com.andrijans.marveltest.presentation.Navigator
import com.andrijans.marveltest.presentation.common.executor.IOThread
import com.andrijans.marveltest.presentation.common.executor.UIThread
import com.andrijans.marveltest.presentation.common.util.NetworkUtil
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun provideContext(application: Application): Context

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideLogger(): ILogger = Logger()

        @JvmStatic
        @Provides
        @Singleton
        fun provideWorkerThread(): IWorkerThread = IOThread()

        @JvmStatic
        @Provides
        @Singleton
        fun provideResultThread(): IResultThread = UIThread()

        @JvmStatic
        @Provides
        fun provideInteractorSubscription(workerThread: IWorkerThread, resultThread: IResultThread): SubscriptionBag = SubscriptionBag(workerThread, resultThread)

        @JvmStatic
        @Provides
        @Singleton
        fun provideNavigator(): Navigator = Navigator()

        @JvmStatic
        @Provides
        @Singleton
        fun provideNetworkUtil(context: Context): NetworkUtil = NetworkUtil(context)

    }


}