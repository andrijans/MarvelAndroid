package com.andrijans.marveltest.domain

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
interface ILogger {
    fun d(message: String?)
    fun d(t: Throwable?)
    fun e(message: String?)
    fun e(t: Throwable?)
}