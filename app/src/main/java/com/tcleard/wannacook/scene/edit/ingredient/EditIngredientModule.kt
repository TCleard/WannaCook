package com.tcleard.wannacook.scene.edit.ingredient

import com.tcleard.wannacook.di.SceneScope
import com.tcleard.wannacook.scene.edit.ingredient.adapter.EditIngredientAdapter
import dagger.Module
import dagger.Provides

@Module
class EditIngredientModule(
        private val fragment: EditIngredientFragment
) {

    @Provides
    @SceneScope
    fun providePresenter(): EditIngredientPresenter {
        return EditIngredientPresenter()
    }

    @Provides
    @SceneScope
    fun provideAdapter(): EditIngredientAdapter {
        return EditIngredientAdapter(fragment)
    }

}