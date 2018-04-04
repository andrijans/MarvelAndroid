package com.andrijans.marveltest.presentation.common.di

import android.app.Application
import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.domain.IResultThread
import com.andrijans.marveltest.domain.IWorkerThread
import com.andrijans.marveltest.framework.api.interactor.SubscriptionBag
import com.andrijans.marveltest.framework.monitoring.Logger
import com.andrijans.marveltest.presentation.Navigator
import com.andrijans.marveltest.presentation.common.executor.IOThread
import com.andrijans.marveltest.presentation.common.executor.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun getApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideLogger(): ILogger = Logger()

    @Provides
    @Singleton
    fun provideWorkerThread(): IWorkerThread = IOThread()

    @Provides
    @Singleton
    fun provideResultThread(): IResultThread = UIThread()

    @Provides
    fun provideInteractorSubscription(workerThread: IWorkerThread, resultThread: IResultThread): SubscriptionBag = SubscriptionBag(workerThread, resultThread)

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = Navigator()

}