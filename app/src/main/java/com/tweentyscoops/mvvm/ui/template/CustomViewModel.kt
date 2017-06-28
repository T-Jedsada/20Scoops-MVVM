package com.tweentyscoops.mvvm.ui.template

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tweentyscoops.mvvm.service.BaseSubscribe
import com.tweentyscoops.mvvm.service.model.MovieDao
import com.tweentyscoops.mvvm.service.model.MovieData
import com.tweentyscoops.mvvm.service.repository.MovieApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CustomViewModel(val movieAPIs: MovieApi) : ViewModel(), BaseSubscribe.SubscribeCallback {

    private var liveData: MutableLiveData<MovieData>? = null
    private var composite = CompositeDisposable()

    fun getMovie(sortBy: String = "popularity.des", page: Int = 1): LiveData<MovieData> {
        when (liveData) {
            null -> {
                liveData = MutableLiveData()
                composite.add(movieAPIs.getMovie(sortBy, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(BaseSubscribe(this)))
            }
        }
        return liveData as MutableLiveData<MovieData>
    }

    override fun <T> onSuccess(dao: T) {
        liveData?.value = MovieData.retrieveMovieSuccess(dao as? MovieDao)
    }

    override fun onFailure(msg: String?) {
        liveData?.value = MovieData.retrieveMovieFailure(msg)
    }

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }

    class Factory(val movieApi: MovieApi) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = CustomViewModel(movieApi) as T
    }
}