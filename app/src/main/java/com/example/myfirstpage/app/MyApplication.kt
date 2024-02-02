package com.example.myfirstpage.app

import android.app.Application
import com.example.myfirstpage.feature.di.AppComponent
import com.example.myfirstpage.feature.di.DaggerAppComponent


class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
