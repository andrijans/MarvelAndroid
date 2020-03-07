package com.andrijans.marveltest.presentation.characters

import com.andrijans.marveltest.domain.IApiService
import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.framework.api.entity.DataContainer
import com.andrijans.marveltest.framework.api.interactor.BaseInteractor
import com.andrijans.marveltest.framework.api.interactor.Listener
import com.andrijans.marveltest.framework.api.interactor.SubscriptionBag
import javax.inject.Inject

class CharactersInteractor @Inject
constructor(apiService: IApiService, logger: ILogger, subscriptionBag: SubscriptionBag) : BaseInteractor(apiService, logger, subscriptionBag), CharactersContract.Interactor {
    override fun getCharacters(offset: Int, listener: Listener<DataContainer>) {
        applyMapErrorAndSubscribe(apiService.getCharacters(offset), listener)
    }

}