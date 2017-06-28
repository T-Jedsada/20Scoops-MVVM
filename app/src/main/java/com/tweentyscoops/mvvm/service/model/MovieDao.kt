package com.tweentyscoops.mvvm.service.model

import com.google.gson.annotations.SerializedName

data class MovieDao(@SerializedName("page") var page: Int,
                    @SerializedName("total_result") val totalResult: Long,
                    @SerializedName("total_pages") val totalPages: Long,
                    @SerializedName("results") val result: MutableList<ResultDetail>) {

    data class ResultDetail(
            @SerializedName("vote_count") val voteCount: Int,
            @SerializedName("id") val id: Long,
            @SerializedName("video") val video: Boolean,
            @SerializedName("vote_average") val voteAverage: java.math.BigDecimal,
            @SerializedName("title") val title: String,
            @SerializedName("popularity") val popularity: java.math.BigDecimal,
            @SerializedName("poster_path") val posterPath: String,
            @SerializedName("original_language") val originalLanguage: String,
            @SerializedName("genre_ids") val genreIds: List<String>,
            @SerializedName("backdrop_path") val backdropPath: String,
            @SerializedName("adult") val adult: Boolean,
            @SerializedName("overview") val overview: String,
            @SerializedName("release_date") val releaseDate: String)
}

sealed class MovieData {
    data class Success(var movieDao: MovieDao) : MovieData()
    data class View(var allResult: MutableList<MovieDao.ResultDetail>, var newResult: MutableList<MovieDao.ResultDetail>) : MovieData()
    data class Failure(val str: String?) : MovieData()

    companion object {
        fun retrieveMovieSuccess(body: MovieDao?) = Success(body!!)
        fun retrieveMovieFailure(msg: String? = "Sorry, somethings error.") = Failure(msg)
    }
}