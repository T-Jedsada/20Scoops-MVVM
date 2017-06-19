package com.tweentyscoops.mvvm

import android.app.Application
import com.tweentyscoops.mvvm.di.AppComponent
import com.tweentyscoops.mvvm.di.AppModule
import com.tweentyscoops.mvvm.di.DaggerAppComponent


class MainApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

    }

    fun component(): AppComponent = appComponent
}
