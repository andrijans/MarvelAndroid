package com.andrijans.marveltest.framework.api.entity.common

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
abstract class Result<T> {
    abstract fun getResultData():T
    abstract fun setResultData(resultData:T)
}