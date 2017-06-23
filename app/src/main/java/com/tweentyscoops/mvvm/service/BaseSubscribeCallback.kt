package com.tweentyscoops.mvvm.service

interface BaseSubscribeCallback {
    fun onUnAuthorized()
    fun onFailure(msg: String?)
}