package com.andrijans.marveltest.framework.api.interactor

import io.reactivex.Observer
import io.reactivex.observers.DisposableObserver

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
open class Listener<T>:DisposableObserver<T>(), Observer<T> {
    override fun onNext(value: T) {
      // empty
    }

    override fun onComplete() {
        // empty
    }

    override fun onError(e: Throwable) {
        // empty
    }
}