package com.tweentyscoops.mvvm

import android.app.Application
import com.tweentyscoops.mvvm.di.*
import timber.log.Timber


class MainApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .retrofitModule(RetrofitModule())
                .apiModule(ApiModule())
                .build()
    }

    fun component(): AppComponent = appComponent
}
