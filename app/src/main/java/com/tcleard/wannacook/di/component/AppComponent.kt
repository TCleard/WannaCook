package com.tcleard.wannacook.di.component

import android.app.Application
import android.content.Context
import com.tcleard.wannacook.core.manager.IDialogManager
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.core.manager.IKeyboardManager
import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.core.repo.local.mock.MockDB
import com.tcleard.wannacook.core.repo.remote.mock.MockApi
import com.tcleard.wannacook.core.service.ARecipeService
import com.tcleard.wannacook.core.service.ATagService
import com.tcleard.wannacook.di.ApplicationScope
import com.tcleard.wannacook.di.module.AppModule
import com.tcleard.wannacook.di.module.RecipeModule
import com.tcleard.wannacook.di.module.TagModule
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, RecipeModule::class,
    TagModule::class])
interface AppComponent {

    fun inject(app: Application)

    // Contexts

    fun application() : Application
    fun context(): Context

    // Mock

    fun mockDB(): MockDB
    fun mockApi(): MockApi

    // Managers

    fun dialogManager(): IDialogManager
    fun imageManager(): IImageManager
    fun keyboardManager(): IKeyboardManager
    fun timeManager(): ITimeManager

    // Services

    fun recipeService(): ARecipeService
    fun tagService(): ATagService

}