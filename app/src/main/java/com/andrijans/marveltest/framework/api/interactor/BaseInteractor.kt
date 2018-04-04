package com.andrijans.marveltest.framework.api.interactor

import com.andrijans.marveltest.domain.IApiService
import com.andrijans.marveltest.domain.IBaseInteractor
import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.framework.api.entity.common.Result
import io.reactivex.Observable

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
open class BaseInteractor(val apiService:IApiService,val logger:ILogger, val subscriptionBag:SubscriptionBag):IBaseInteractor {
    protected fun <T> addMapAndError(observable: Observable<out Result<T>>): Observable<T> {
        return observable
                .doOnError { error -> logger.e(error) }
                .map { listResult: Result<T> -> listResult.getResultData() }
    }

    protected fun <T> addError(observable: Observable<T>): Observable<T> {
        return observable
                .doOnError { error -> logger.e(error) }
    }

    protected fun <T> applyErrorAndSubscribe(observable: Observable<T>, listener: Listener<T>) {
        subscriptionBag.add(addError(observable), listener)
    }

    protected fun <T> applyMapErrorAndSubscribe(observable: Observable<out Result<T>>, listener: Listener<T>) {
        subscriptionBag.add(addMapAndError(observable), listener)
    }

    override fun unsubscribe() {
        subscriptionBag.unsubscribe()
    }
}