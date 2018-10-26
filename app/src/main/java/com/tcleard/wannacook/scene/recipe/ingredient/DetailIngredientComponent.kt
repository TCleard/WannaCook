package com.tcleard.wannacook.scene.recipe.ingredient

import dagger.Component
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [DetailIngredientModule::class]
)
interface DetailIngredientComponent {

    fun inject(fragment: DetailIngredientFragment)

}