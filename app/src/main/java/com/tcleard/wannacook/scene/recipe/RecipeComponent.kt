package com.tcleard.wannacook.scene.recipe

import dagger.Component
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [RecipeModule::class]
)
interface RecipeComponent {

    fun inject(activity: RecipeActivity)

}