package com.tweentyscoops.mvvm.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication() = application

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences = application.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
}