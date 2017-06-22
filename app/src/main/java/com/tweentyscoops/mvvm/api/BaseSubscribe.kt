package com.tweentyscoops.mvvm.api

import io.reactivex.subscribers.DefaultSubscriber

class BaseSubscribe<T> : DefaultSubscriber<T>() {
    override fun onComplete() {

    }

    override fun onError(t: Throwable?) {

    }

    override fun onNext(t: T) {

    }
}