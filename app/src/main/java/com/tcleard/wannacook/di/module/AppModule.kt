package com.tcleard.wannacook.di.module

import android.app.Application
import android.content.Context
import com.tcleard.wannacook.core.manager.IDialogManager
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.core.manager.IKeyboardManager
import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.core.manager.impl.GlideImageManager
import com.tcleard.wannacook.core.manager.impl.KeyboardManager
import com.tcleard.wannacook.core.manager.impl.TimeManager
import com.tcleard.wannacook.core.manager.impl.dialog.CustomDialogManager
import com.tcleard.wannacook.core.manager.impl.dialog.NativeDialogManager
import com.tcleard.wannacook.core.repo.local.mock.MockDB
import com.tcleard.wannacook.core.repo.remote.mock.MockApi
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
    fun provideContext(): Context = app

    @Provides
    @ApplicationScope
    fun provideDialogManager(): IDialogManager {
        return CustomDialogManager()
    }

    @Provides
    @ApplicationScope
    fun provideImageManager(): IImageManager {
        return GlideImageManager(app)
    }

    @Provides
    @ApplicationScope
    fun provideKeyboardManager(): IKeyboardManager {
        return KeyboardManager()
    }

    @Provides
    @ApplicationScope
    fun provideTimeManager(): ITimeManager {
        return TimeManager()
    }

    @Provides
    @ApplicationScope
    fun provideMockDB(): MockDB {
        return MockDB()
    }

    @Provides
    @ApplicationScope
    fun provideMockApi(): MockApi {
        return MockApi()
    }

}