package com.tcleard.wannacook.scene.edit

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.extension.setHomeIcon
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.ui.controller.AActivity
import kotlinx.android.synthetic.main.activity_edit_recipe.*
import kotlinx.android.synthetic.main.include_toolbar.view.*

class EditRecipeActivity : AActivity<EditRecipePresenter>(), EditRecipePresenter.EditView {

    companion object {

        private const val EXTRA_RECIPE = "recipe"

        fun builder(context: Context): Builder = Builder(context)

        class Builder(context: Context) : AActivity.Builder(context) {

            override val activityClass = EditRecipeActivity::class.java

            fun with(recipe: Recipe): Builder {
                intent.putExtra(EXTRA_RECIPE, recipe)
                return this
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_recipe)

        setSupportActionBar(editToolbarLayout.toolbar)
        supportActionBar?.setHomeIcon(R.mipmap.back)

        DaggerEditRecipeComponent.builder()
                .appComponent(appComponent)
                .editRecipeModule(EditRecipeModule())
                .build()
                .inject(this)

        presenter.attach(this)

        presenter.setRecipe(intent.getSerializableExtra(EXTRA_RECIPE) as? Recipe)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    /** EditView **/

    override fun showTitle(titleRes: Int) {
        supportActionBar?.setTitle(titleRes)
    }
}