package com.andrijans.marveltest.framework.api.interactor

import com.andrijans.marveltest.domain.IResultThread
import com.andrijans.marveltest.domain.IWorkerThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class SubscriptionBag(val workerThread:IWorkerThread, val resultThread:IResultThread) {
    private val disposables=CompositeDisposable()

    fun <T> add(observable: Observable<T>, subscriber: DisposableObserver<T>) {
        this.disposables.add(observable
                .subscribeOn(workerThread.getScheduler())
                .observeOn(resultThread.getScheduler())
                .subscribeWith(subscriber)
        )
    }

    fun unsubscribe(){
        disposables.clear()
    }
}