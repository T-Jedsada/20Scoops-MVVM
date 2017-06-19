package com.tweentyscoops.mvvm.di

import com.tweentyscoops.mvvm.MainActivity
import com.tweentyscoops.mvvm.ui.template.CustomActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: CustomActivity)
}