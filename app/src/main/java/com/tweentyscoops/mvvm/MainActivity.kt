package com.tweentyscoops.mvvm

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var spf: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainApplication.appComponent.inject(this)

        setupView()
        setupInstance()
        if (savedInstanceState == null) initialize()
    }

    private fun setupView() {

    }

    private fun setupInstance() {

    }

    private fun initialize() {

    }

    override fun onStart() {
        super.onStart()
        somethingsA()
        somethingsB()
    }

    override fun onStop() {
        super.onStop()
        somethingsC()
        somethingsD()
    }

    private fun somethingsA() {

    }

    private fun somethingsB() {

    }

    private fun somethingsC() {

    }

    private fun somethingsD() {

    }
}