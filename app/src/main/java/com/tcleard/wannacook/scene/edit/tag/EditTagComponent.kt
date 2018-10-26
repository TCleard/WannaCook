package com.tcleard.wannacook.scene.edit.tag

import dagger.Component
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [EditTagModule::class]
)
interface EditTagComponent {

    fun inject(fragment: EditTagFragment)

}