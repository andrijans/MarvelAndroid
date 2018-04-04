package com.andrijans.marveltest.framework.monitoring

import com.andrijans.marveltest.domain.ILogger
import timber.log.Timber

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class Logger:ILogger {
    override fun d(message: String?) {
       Timber.d(message)
    }

    override fun d(t: Throwable?) {
        Timber.d(t)
    }

    override fun e(message: String?) {
       Timber.e(message)
    }

    override fun e(t: Throwable?) {
       Timber.e(t)
    }
}