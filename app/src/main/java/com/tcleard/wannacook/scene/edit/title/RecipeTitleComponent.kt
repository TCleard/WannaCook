package com.tcleard.wannacook.scene.edit.title

import dagger.Component
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [RecipeTitleModule::class]
)
interface RecipeTitleComponent {

    fun inject(fragment: RecipeTitleFragment)

}