package com.tweentyscoops.mvvm.api

import io.reactivex.subscribers.DefaultSubscriber

class BaseSubscribe<T> : DefaultSubscriber<T>() {
    override fun onComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNext(t: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}