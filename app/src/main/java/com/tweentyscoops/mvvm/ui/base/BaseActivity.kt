package com.tweentyscoops.mvvm.ui.base

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tweentyscoops.mvvm.MainApplication
import com.tweentyscoops.mvvm.di.AppComponent
import com.tweentyscoops.mvvm.service.BaseSubscribeCallback
import com.tweentyscoops.mvvm.ui.exception.NotSetLayoutException
import timber.log.Timber

@Suppress("LeakingThis")
abstract class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner, BaseSubscribeCallback {

    private val mRegister = LifecycleRegistry(this)

    abstract fun layoutToInflate(): Int
    abstract fun setupView()
    abstract fun setupInstance()
    abstract fun initialize()
    abstract fun startView()
    abstract fun stopView()
    abstract fun doInjection(appComponent: AppComponent)
    override fun getLifecycle(): LifecycleRegistry = mRegister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutToInflate() == 0) throw  NotSetLayoutException()
        setContentView(layoutToInflate())
        doInjection((application as MainApplication).component())
        setupView()
        setupInstance()
        if (savedInstanceState == null) initialize()
    }

    override fun onStart() {
        super.onStart()
        startView()
    }

    override fun onStop() {
        super.onStop()
        stopView()
    }

    override fun onUnAuthorized() {
        Timber.e("unAuthorized")
    }

    override fun onFailure(msg: String?) {
        Timber.e(msg)
    }
}