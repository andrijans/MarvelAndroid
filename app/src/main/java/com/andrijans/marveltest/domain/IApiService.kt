package com.andrijans.marveltest.domain

import com.andrijans.marveltest.framework.api.entity.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
interface IApiService {

    @GET("characters")
    fun getCharacters(
            @Query("offset") offset: Int
    ): Observable<Response>

    @GET("characters/{id}")
    fun getCharacter(
            @Path("id") id: Int
    ): Observable<Response>

}