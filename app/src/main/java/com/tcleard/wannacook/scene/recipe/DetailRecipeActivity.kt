package com.tcleard.wannacook.scene.recipe

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.View
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.extension.withAlpha
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.ui.controller.AActivity
import com.tcleard.wannacook.ui.controller.IController
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import javax.inject.Inject

class DetailRecipeActivity : AActivity<DetailRecipePresenter>(), DetailRecipePresenter.RecipeView, View.OnClickListener {

    companion object {

        private val EXTRA_RECIPE = "recipe"

        fun builder(controller: IController): Builder = Builder(controller)

        class Builder(controller: IController) : AActivity.Builder(controller) {

            override val activityClass = DetailRecipeActivity::class.java

            fun with(recipe: Recipe): Builder {
                intent.putExtra(EXTRA_RECIPE, recipe)
                return this
            }

        }

    }

    @Inject
    lateinit var adapter: DetailRecipeFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        recipeBack.setOnClickListener(this)

        supportPostponeEnterTransition()

        DaggerDetailRecipeComponent.builder()
                .appComponent(appComponent)
                .detailRecipeModule(DetailRecipeModule(this))
                .build()
                .inject(this)

        presenter.attach(this)

        recipePager.adapter = adapter

        (intent.getSerializableExtra(EXTRA_RECIPE) as? Recipe)?.let {
            presenter.setRecipe(it)
        }

    }

    override fun onStart() {
        super.onStart()
        setLayoutBelowStatusBar(true)
    }

    override fun onStop() {
        super.onStop()
        setLayoutBelowStatusBar(false)
    }

    override fun onBackPressed() {
        if (recipeRoot.progress > 0f && recipeRoot.currentState != -1) {
            recipeRoot.currentState
            recipeRoot.transitionToStart()
        } else {
            super.onBackPressed()
        }
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

    override fun showRecipeColor(color: Int?) {
        val uColor = color
                ?: resources.getColor(R.color.colorPrimary)
        recipeImage.setBackgroundColor(uColor.withAlpha(0.4f))
        recipeName.setTextColor(uColor)
        recipePagerIndicator.selectedColor = uColor
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            recipeExpander.backgroundTintList = ColorStateList.valueOf(uColor)
            recipeBackground.backgroundTintList = ColorStateList.valueOf(uColor)
        }
    }

    override fun showName(name: String) {
        recipeName.text = name
        recipeNameWhite.text = name
    }

    override fun showFragments(fragments: List<ADetailRecipeFragment<*>>) {
        adapter.setFragments(fragments)
    }

    override fun showPreparationTime(preparation: String?) {
        if (preparation != null) {
            recipePreparationIcon.visibility = View.VISIBLE
            recipePreparationText.visibility = View.VISIBLE
            recipePreparationText.text = preparation
        } else {
            recipePreparationIcon.visibility = View.GONE
            recipePreparationText.visibility = View.GONE
        }
    }

    override fun showCookingTime(cooking: String?) {
        if (cooking != null) {
            recipeCookingIcon.visibility = View.VISIBLE
            recipeCookingText.visibility = View.VISIBLE
            recipeCookingText.text = cooking
        } else {
            recipeCookingIcon.visibility = View.GONE
            recipeCookingText.visibility = View.GONE
        }
    }

    override fun showLevel(level: Int) {
        recipeDifficultyText.setText(level)
    }

    /** Listeners **/

    override fun onClick(v: View?) {
        when (v) {
            recipeBack -> super.onBackPressed()
        }
    }

}