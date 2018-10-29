package com.tcleard.wannacook.scene.edit.ingredient

import dagger.Component
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [EditIngredientModule::class]
)
interface EditIngredientComponent {

    fun inject(fragment: EditIngredientFragment)

}