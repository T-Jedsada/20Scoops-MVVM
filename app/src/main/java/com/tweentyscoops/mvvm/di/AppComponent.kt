package com.tweentyscoops.mvvm.di

import com.tweentyscoops.mvvm.ui.template.CustomActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class))
interface AppComponent {
    fun inject(customActivity: CustomActivity)
}