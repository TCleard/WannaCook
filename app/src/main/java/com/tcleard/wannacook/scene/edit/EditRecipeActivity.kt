package com.tcleard.wannacook.scene.edit

import android.os.Bundle
import android.view.View
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.extension.rx.onIoToMain
import com.tcleard.wannacook.core.extension.rx.sub
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.ui.controller.AActivity
import com.tcleard.wannacook.ui.controller.IController
import kotlinx.android.synthetic.main.activity_edit_recipe.*
import javax.inject.Inject

class EditRecipeActivity : AActivity<EditRecipePresenter>(), EditRecipePresenter.EditView, View.OnClickListener {

    companion object {

        private const val EXTRA_RECIPE = "recipe"

        fun builder(controller: IController): Builder = Builder(controller)

        class Builder(controller: IController) : AActivity.Builder(controller) {

            override val activityClass = EditRecipeActivity::class.java

            fun with(recipe: Recipe): Builder {
                intent.putExtra(EXTRA_RECIPE, recipe)
                return this
            }

        }

    }

    @Inject
    lateinit var adapter: EditRecipeFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_recipe)

        DaggerEditRecipeComponent.builder()
                .appComponent(appComponent)
                .editRecipeModule(EditRecipeModule(this))
                .build()
                .inject(this)

        presenter.attach(this)

        keyboardManager.listenKeyboardStateChanged()
                .onIoToMain()
                .sub(onNext = { isKeyboardOpen ->
                    editBottom.visibility = if (isKeyboardOpen) {
                        View.GONE
                    } else {
                        View.VISIBLE
                    }
                })

        editPager.adapter = adapter
        adapter.onFragmentSelected = {
            presenter.onFragmentSelected(it)
        }

        editLeft.setOnClickListener(this)
        editRight.setOnClickListener(this)

        presenter.setRecipe(intent.getSerializableExtra(EXTRA_RECIPE) as? Recipe)

    }

    override fun onBackPressed() {
        presenter.onLeftButtonClicked()
    }

    /** EditView **/

    override fun showTitle(titleRes: Int) {
        supportActionBar?.setTitle(titleRes)
    }

    override fun showFragments(fragments: List<AEditRecipeFragment<*>>) {
        adapter.setFragments(fragments)
        editPager.currentItem = 0
    }

    override fun showLeftButton(textRes: Int) {
        editLeft.setText(textRes)
    }

    override fun showRightButton(textRes: Int) {
        editRight.setText(textRes)
    }

    override fun showNextButtonEnabled(enable: Boolean) {
        editRight.isEnabled = enable
    }

    override fun goToNext() {
        editPager.goToNext()
        keyboardManager.closeKeyboard()
    }

    override fun goToPrevious() {
        editPager.goToPrevious()
        keyboardManager.closeKeyboard()
    }

    override fun quit() {
        finish()
    }

    /** Listeners **/

    override fun onClick(v: View?) {
        when (v) {
            editLeft -> presenter.onLeftButtonClicked()
            editRight -> presenter.onRightButtonClicked()
        }
    }

}