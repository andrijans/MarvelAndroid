package com.andrijans.marveltest.framework.api

import com.andrijans.marveltest.domain.IApiService
import com.andrijans.marveltest.presentation.common.constants.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
@Module
abstract class ApiModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideHttpClient(): okhttp3.OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val authenticationInterceptor = AuthInterceptor()

            val httpClient = okhttp3.OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(authenticationInterceptor)
                    .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                    .build()

            return httpClient
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideRestAdapter(httpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.API_URL)
                    .client(httpClient)
                    .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideApiService(restAdapter: Retrofit): IApiService = restAdapter.create(IApiService::class.java)
    }


}