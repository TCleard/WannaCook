package com.tcleard.wannacook.scene.recipe.step

import dagger.Component
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [DetailStepModule::class]
)
interface DetailStepComponent {

    fun inject(fragment: DetailStepFragment)

}