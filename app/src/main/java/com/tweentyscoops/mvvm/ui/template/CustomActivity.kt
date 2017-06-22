package com.tweentyscoops.mvvm.ui.template

import com.tweentyscoops.mvvm.R
import com.tweentyscoops.mvvm.di.AppComponent
import com.tweentyscoops.mvvm.service.BaseSubscribe
import com.tweentyscoops.mvvm.service.model.MovieDao
import com.tweentyscoops.mvvm.service.repository.MovieApi
import com.tweentyscoops.mvvm.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CustomActivity : BaseActivity(), BaseSubscribe.SubscribeCallback {

    @Inject
    lateinit var movieAPIs: MovieApi

    override fun doInjection(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun layoutToInflate() = R.layout.activity_main

    override fun setupView() {

    }

    override fun setupInstance() {

    }

    override fun initialize() {
        movieAPIs.getMovie("popularity.des", 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubscribe(this))
    }

    override fun startView() {

    }

    override fun stopView() {

    }

    override fun <T> onSuccess(t: T) {
        when(t) {
            is MovieDao -> Timber.e("OK")
            else -> Timber.e("ON")
        }
    }

    override fun onFailure(msg: String?) {
        Timber.e(msg)
    }
}