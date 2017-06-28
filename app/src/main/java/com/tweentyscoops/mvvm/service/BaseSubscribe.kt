package com.tweentyscoops.mvvm.service

import io.reactivex.observers.DisposableObserver
import retrofit2.Response

class BaseSubscribe<T>(var callback: SubscribeCallback) : DisposableObserver<Response<T>>() {

    interface SubscribeCallback {
        fun <T> onSuccess(dao: T)
        fun onFailure(msg: String?)
    }

    override fun onComplete() {
        // nothings
    }

    override fun onNext(t: Response<T>?) {
        if (t?.isSuccessful!!) {
            callback.onSuccess(t.body())
        } else {
            callback.onFailure(t.message())
        }
    }

    override fun onError(e: Throwable?) {
        callback.onFailure(e?.message)
    }
}