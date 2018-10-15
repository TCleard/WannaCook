package com.tcleard.wannacook.scene.recipe

import android.content.Context
import android.os.Bundle
import android.view.View
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.ui.controller.AActivity
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AActivity<RecipePresenter>(), RecipePresenter.RecipeView, View.OnClickListener {

    companion object {

        private val EXTRA_RECIPE = "recipe"

        fun builder(context: Context): Builder = Builder(context)

        class Builder(context: Context) : AActivity.Builder(context) {

            override val activityClass = RecipeActivity::class.java

            fun with(recipe: Recipe): Builder {
                intent.putExtra(EXTRA_RECIPE, recipe)
                return this
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        setLayoutBelowStatusBar(true)

        recipeBack.setOnClickListener(this)

        supportPostponeEnterTransition()

        DaggerRecipeComponent.builder()
                .appComponent(appComponent)
                .recipeModule(RecipeModule())
                .build()
                .inject(this)

        presenter.attach(this)

        (intent.getSerializableExtra(EXTRA_RECIPE) as? Recipe)?.let {
            presenter.setRecipe(it)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        setLayoutBelowStatusBar(false)
    }

    /** RecipeView **/

    private var recipeImageRequest: IImageManager.ImageRequest? = null

    override fun showImage(imageUrl: String) {
        if (imageUrl.isNotBlank()) {
            if (recipeImageRequest?.url != imageUrl) {
                recipeImageRequest?.cancel()
                recipeImageRequest = imageManager.loadImage(imageUrl, recipeImage) {
                    supportStartPostponedEnterTransition()
                }
            }
        } else {
            recipeImageRequest?.cancel()
            recipeImageRequest = null
            recipeImage.setImageDrawable(null)
        }
    }

    override fun showImageBackground(color: Int?) {
        recipeImageBackground.setBackgroundColor((color
                ?: resources.getColor(R.color.colorPrimary)))
    }

    override fun showName(name: String) {
        recipeName.text = name
    }

    /** Listeners **/

    override fun onClick(v: View?) {
        when (v) {
            recipeBack -> onBackPressed()
        }
    }

}