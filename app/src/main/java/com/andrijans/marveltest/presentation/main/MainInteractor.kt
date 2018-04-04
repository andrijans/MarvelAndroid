package com.andrijans.marveltest.presentation.main

import com.andrijans.marveltest.domain.IApiService
import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.framework.api.entity.DataContainer
import com.andrijans.marveltest.framework.api.interactor.BaseInteractor
import com.andrijans.marveltest.framework.api.interactor.Listener
import com.andrijans.marveltest.framework.api.interactor.SubscriptionBag
import javax.inject.Inject

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class MainInteractor @Inject
constructor(apiService:IApiService,logger:ILogger,subscriptionBag: SubscriptionBag): BaseInteractor(apiService,logger,subscriptionBag),MainContract.Interactor {

    override fun getCharacters(offset:Int,listener: Listener<DataContainer>) {
        applyMapErrorAndSubscribe(apiService.getCharacters(offset),listener)
    }
}