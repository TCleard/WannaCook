package com.tcleard.wannacook.di.component

import android.app.Application
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.core.repo.local.mock.MockDB
import com.tcleard.wannacook.core.repo.remote.mock.MockApi
import com.tcleard.wannacook.core.service.ARecipeService
import com.tcleard.wannacook.di.ApplicationScope
import com.tcleard.wannacook.di.module.AppModule
import com.tcleard.wannacook.di.module.RecipeModule
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, RecipeModule::class])
interface AppComponent {

    fun inject(app: Application)

    // Mock

    fun mockDB(): MockDB
    fun mockApi(): MockApi

    // Managers

    fun imageManager(): IImageManager
    fun timeManager(): ITimeManager

    // Services

    fun recipeService(): ARecipeService

}