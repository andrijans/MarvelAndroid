package com.andrijans.marveltest.domain

import io.reactivex.Scheduler

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
interface IWorkerThread {
    fun getScheduler(): Scheduler
}