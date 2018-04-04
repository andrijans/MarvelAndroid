package com.andrijans.marveltest.framework.api

import com.andrijans.marveltest.presentation.App
import com.andrijans.marveltest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.experimental.and
import kotlin.experimental.or


/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class AuthInterceptor : Interceptor {
    companion object {
        val TIMESTAMP_KEY = "ts"
        val HASH_KEY = "hash"
        val API_KEY = "apikey"

    }
    init {
        App.appComponent.inject(this)
    }

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val timestamp = System.currentTimeMillis().toString()
        val hash = generateHash(timestamp, BuildConfig.MARVEL_PUBLIC_KEY, BuildConfig.MARVEL_PRIVATE_KEY)
        var request = chain?.request()
        val url = request?.url()?.newBuilder()
                ?.addQueryParameter(TIMESTAMP_KEY, timestamp)
                ?.addQueryParameter(API_KEY, BuildConfig.MARVEL_PUBLIC_KEY)
                ?.addQueryParameter(HASH_KEY, hash)
                ?.build()
        request = request?.newBuilder()?.url(url)?.build()
        return chain?.proceed(request)
    }


    fun generateHash(timestamp: String, publicKey: String, privateKey: String): String {
        val value = timestamp + privateKey + publicKey
        val md5Encoder = MessageDigest.getInstance("MD5")
        val md5Bytes = md5Encoder.digest(value.toByteArray())
        return BigInteger(1,md5Bytes).toString(16)
    }
}