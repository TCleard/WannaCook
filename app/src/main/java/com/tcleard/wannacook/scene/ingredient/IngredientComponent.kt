package com.tcleard.wannacook.scene.ingredient

import dagger.Component
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [IngredientModule::class]
)
interface IngredientComponent {

    fun inject(activity: IngredientActivity)

}