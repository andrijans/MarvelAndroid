package com.andrijans.marveltest.framework.api

import com.andrijans.marveltest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest


/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class AuthInterceptor : Interceptor {
    companion object {
        const val TIMESTAMP_KEY = "ts"
        const val HASH_KEY = "hash"
        const val API_KEY = "apikey"

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        val hash = generateHash(timestamp)
        var request = chain.request()
        val url = request.url.newBuilder()
                .addQueryParameter(TIMESTAMP_KEY, timestamp)
                .addQueryParameter(API_KEY, BuildConfig.MARVEL_PUBLIC_KEY)
                .addQueryParameter(HASH_KEY, hash)
                .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }


    private fun generateHash(timestamp: String): String {
        val value = timestamp + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY
        val md5Encoder = MessageDigest.getInstance("MD5")
        val md5Bytes = md5Encoder.digest(value.toByteArray())
        return BigInteger(1, md5Bytes).toString(16)
    }
}