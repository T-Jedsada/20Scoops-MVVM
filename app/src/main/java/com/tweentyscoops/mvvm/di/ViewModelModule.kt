package com.tweentyscoops.mvvm.di

import com.tweentyscoops.mvvm.service.repository.MovieApi
import com.tweentyscoops.mvvm.ui.template.CustomViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun providesCustomViewModel(movieApi: MovieApi): CustomViewModel.Factory =
            CustomViewModel.Factory(movieApi)
}