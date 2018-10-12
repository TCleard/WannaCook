package com.tcleard.wannacook.di.module

import android.app.Application
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.core.manager.impl.GlideImageManager
import com.tcleard.wannacook.core.manager.impl.TimeManager
import com.tcleard.wannacook.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {

    @Provides
    @ApplicationScope
    fun provideApplication(): Application = app

    @Provides
    @ApplicationScope
    fun provideImageManager(): IImageManager {
        return GlideImageManager(app)
    }

    @Provides
    @ApplicationScope
    fun provideTimeManager(): ITimeManager {
        return TimeManager()
    }

}