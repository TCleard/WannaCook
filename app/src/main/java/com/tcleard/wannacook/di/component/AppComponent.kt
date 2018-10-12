package com.tcleard.wannacook.di.component

import android.app.Application
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.di.ApplicationScope
import com.tcleard.wannacook.di.module.AppModule
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: Application)

    // Managers
    fun imageManager(): IImageManager
    fun timeManager(): ITimeManager

}