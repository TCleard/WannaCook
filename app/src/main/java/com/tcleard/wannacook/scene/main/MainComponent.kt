package com.tcleard.wannacook.scene.main

import dagger.Component
import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [MainModule::class]
)
interface MainComponent {

    fun inject(activity: MainActivity)

}