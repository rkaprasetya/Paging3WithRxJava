package com.example.tandemtest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App:Application() {
    open fun getApiUrl():String{
        return "http://apiurl"
    }
}