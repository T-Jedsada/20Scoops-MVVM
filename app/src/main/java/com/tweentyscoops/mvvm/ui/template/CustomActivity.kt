package com.tweentyscoops.mvvm.ui.template

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import com.tweentyscoops.mvvm.R
import com.tweentyscoops.mvvm.di.AppComponent
import com.tweentyscoops.mvvm.ui.base.BaseActivity
import javax.inject.Inject

class CustomActivity : BaseActivity() {

    private val TAG: String = CustomActivity::class.java.simpleName

    @Inject
    lateinit var customViewModelFactory: CustomViewModel.Factory

    private var customViewModel: CustomViewModel? = null

    override fun doInjection(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun layoutToInflate() = R.layout.activity_main

    override fun setupView() {
        customViewModel = ViewModelProviders.of(this, customViewModelFactory)
                .get(CustomViewModel::class.java)
    }

    override fun setupInstance() {

    }

    override fun initialize() {

    }

    override fun startView() {
        customViewModel?.getMovie()?.observe(this, Observer {
            Log.d(TAG, "$it")
        })
    }

    override fun stopView() {

    }
}