package com.andrijans.marveltest.presentation.assistance

import com.andrijans.marveltest.domain.ILogger
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AssistanceModule {
    @Binds
    abstract fun bindView(fragment: AssistanceFragment):AssistanceContract.View

    @Module
    companion object{
        @JvmStatic
        @Provides
        fun providePresenter(view:AssistanceContract.View,logger:ILogger):AssistanceContract.Presenter=AssistancePresenterImpl(view,logger)
    }
}