package com.tcleard.wannacook.scene.main.adapter.vm

import com.tcleard.wannacook.core.manager.ITimeManager
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.ui.adapter.AViewModel

class RecipeViewModel(
        recipe: Recipe,
        private val timeManager: ITimeManager
) : AViewModel<Recipe>(recipe) {

    fun getImageUrl(): String = model.imageUrl

    fun getType(): String = model.type.name
    fun getTypeColor(): Int? = model.type.color

    fun getName(): String = model.name

    fun getPreparationTime(): String? = model.preparationTime?.let { timeManager.getDuration(it) }

    fun getCookingTime(): String? = model.cookingTime?.let { timeManager.getDuration(it) }

    fun isFavorite() : Boolean = getName().contains("a")

}