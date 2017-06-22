package com.tweentyscoops.mvvm.service

import io.reactivex.observers.DisposableObserver
import retrofit2.Response
import java.net.HttpURLConnection

class BaseSubscribe<T>(var callback: SubscribeCallback) : DisposableObserver<Response<T>>() {

    interface SubscribeCallback {
        fun <T> onSuccess(t: T)
        fun onFailure(msg: String?)
    }

    override fun onComplete() {
        // nothings
    }

    override fun onNext(t: Response<T>?) {
        if (t?.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            // TODO : HTTP_UNAUTHORIZED
        } else if (t?.isSuccessful!!) {
            callback.onSuccess(t.body())
        } else {
            callback.onFailure(t.message())
        }
    }

    override fun onError(e: Throwable?) {
        callback.onFailure(e?.message)
    }
}