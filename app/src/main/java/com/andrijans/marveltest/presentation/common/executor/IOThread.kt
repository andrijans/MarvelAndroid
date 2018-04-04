package com.andrijans.marveltest.presentation.common.executor

import com.andrijans.marveltest.domain.IWorkerThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by andrijanstankovic on 31/08/2017.
 */
class IOThread : IWorkerThread {
    override fun getScheduler(): Scheduler = Schedulers.io()
}