package com.tweentyscoops.mvvm.ui.template

import android.content.SharedPreferences
import com.tweentyscoops.mvvm.R
import com.tweentyscoops.mvvm.di.AppComponent
import com.tweentyscoops.mvvm.ui.base.BaseActivity
import javax.inject.Inject

class CustomActivity : BaseActivity() {

    @Inject
    lateinit var spf : SharedPreferences

    override fun doInjection(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun layoutToInflate() = R.layout.activity_main

    override fun setupView() {
        val isLogin = spf.getBoolean("is_login", false)

    }

    override fun setupInstance() {

    }

    override fun initialize() {

    }

    override fun startView() {

    }

    override fun stopView() {

    }
}