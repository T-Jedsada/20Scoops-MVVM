package com.tweentyscoops.mvvm.service

import io.reactivex.observers.DisposableObserver
import retrofit2.Response
import java.net.HttpURLConnection

class BaseSubscribe<T>(var callback: SubscribeSubscribeCallback) : DisposableObserver<Response<T>>() {

    interface SubscribeSubscribeCallback : BaseSubscribeCallback {
        fun <T> onSuccess(dao: T)
        fun onError(msg: String?)
    }

    override fun onComplete() {
        // nothings
    }

    override fun onNext(t: Response<T>?) {
        if (t?.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            callback.onUnAuthorized()
        } else if (t?.isSuccessful!!) {
            callback.onSuccess(t.body())
        } else {
            callback.onError(t.message())
        }
    }

    override fun onError(e: Throwable?) {
        callback.onFailure(e?.message)
    }
}