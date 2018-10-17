package com.tcleard.wannacook.scene.edit

import dagger.Component
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [EditRecipeModule::class]
)
interface EditRecipeComponent {

    fun inject(activity: EditRecipeActivity)

}