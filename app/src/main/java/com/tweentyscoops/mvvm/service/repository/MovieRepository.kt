package com.tweentyscoops.mvvm.service.repository

import com.tweentyscoops.mvvm.service.model.MovieDao
import com.tweentyscoops.mvvm.service.model.MovieData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

open class MovieRepository(var movieAPIs: MovieApi) {

    private var nextPageAvailable = true
    private val MAX_PAGE = 6
    private var page = 1

    interface MovieModelCallback {
        fun requestMovieSuccess(data: MovieData.Success)
        fun requestMovieError(msg: String?)
    }

    fun observableMovie(sortBy: String, page: Int): Observable<Response<MovieDao>> =
            movieAPIs.getMovie(sortBy, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    fun requestMovie(sortBy: String, callback: MovieModelCallback) {
        observableMovie(sortBy, getNextPage()).subscribe({
            when (it.isSuccessful) {
                true -> {
                    nextPageAvailable = page < MAX_PAGE
                    callback.requestMovieSuccess(MovieData.retrieveMovieSuccess(it.body()))
                }
                else -> callback.requestMovieError(it.message())
            }
        }, {
            nextPageAvailable = false
            callback.requestMovieError(it.message)
        })
    }

    fun nextPageAvailable() = nextPageAvailable

    private fun getNextPage() = page++
}