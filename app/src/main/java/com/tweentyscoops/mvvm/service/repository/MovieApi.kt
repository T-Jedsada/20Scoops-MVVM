package com.tweentyscoops.mvvm.service.repository

import com.tweentyscoops.mvvm.service.model.MovieDao
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie?api_key=6c26bbd637c722ffab43dc6984053411")
    fun getMovie(@Query("sort_by") sortBy: String, @Query("page") page: Int): Observable<Response<MovieDao>>
}