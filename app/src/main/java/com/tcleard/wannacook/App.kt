package com.tcleard.wannacook

import android.app.Application
import com.tcleard.wannacook.di.component.AppComponent
import com.tcleard.wannacook.di.module.AppModule
import com.tcleard.wannacook.di.component.DaggerAppComponent

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}