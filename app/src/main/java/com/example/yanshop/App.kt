package com.example.yanshop

import android.app.Application
import com.example.yanshop.di.AppComponent
import com.example.yanshop.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}