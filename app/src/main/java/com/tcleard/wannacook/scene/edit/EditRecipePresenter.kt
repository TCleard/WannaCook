package com.tcleard.wannacook.scene.edit

import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.extension.rx.sub
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.scene.edit.ingredient.EditIngredientFragment
import com.tcleard.wannacook.scene.edit.tag.EditTagFragment
import com.tcleard.wannacook.scene.edit.title.EditTitleFragment
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class EditRecipePresenter @Inject constructor(

) : APresenter<EditRecipePresenter.EditView>() {

    private var recipe: Recipe? = null

    private var disposable: Disposable? = null

    private lateinit var leftButton: AEditRecipeFragment.LeftButton
    private lateinit var rightButton: AEditRecipeFragment.RightButton

    fun setRecipe(recipe: Recipe?) {
        this.recipe = recipe
        if (recipe != null) {
            view?.showTitle(R.string.editOld)
        } else {
            view?.showTitle(R.string.editNew)
        }
        val fragments = arrayListOf<AEditRecipeFragment<*>>()

        val titleFragment = EditTitleFragment()
        titleFragment.recipe = recipe
        titleFragment.leftButton = AEditRecipeFragment.LeftButton.QUIT
        titleFragment.rightButton = AEditRecipeFragment.RightButton.NEXT
        fragments.add(titleFragment)

        val tagFragment = EditTagFragment()
        tagFragment.recipe = recipe
        tagFragment.leftButton = AEditRecipeFragment.LeftButton.BACK
        tagFragment.rightButton = AEditRecipeFragment.RightButton.NEXT
        fragments.add(tagFragment)

        val ingredientFragment = EditIngredientFragment()
        ingredientFragment.recipe = recipe
        ingredientFragment.leftButton = AEditRecipeFragment.LeftButton.BACK
        ingredientFragment.rightButton = AEditRecipeFragment.RightButton.NEXT
        fragments.add(ingredientFragment)

        view?.showFragments(fragments)
    }

    fun onFragmentSelected(fragment: AEditRecipeFragment<*>) {
        disposable?.dispose()
        disposable = fragment.watchState()
                .sub(onNext = {
                    view?.showNextButtonEnabled(it)
                })
        leftButton = fragment.leftButton
        rightButton = fragment.rightButton
        view?.showLeftButton(fragment.leftButton.res)
        view?.showRightButton(fragment.rightButton.res)
    }

    fun onLeftButtonClicked() {
        when (leftButton) {
            AEditRecipeFragment.LeftButton.BACK -> view?.goToPrevious()
            AEditRecipeFragment.LeftButton.QUIT -> view?.quit()
        }
    }

    fun onRightButtonClicked() {
        when (rightButton) {
            AEditRecipeFragment.RightButton.NEXT -> view?.goToNext()
        }
    }

    interface EditView : IView {

        fun showTitle(titleRes: Int)

        fun showFragments(fragments: List<AEditRecipeFragment<*>>)

        fun showLeftButton(textRes: Int)
        fun showRightButton(textRes: Int)
        fun showNextButtonEnabled(enable: Boolean)

        fun goToNext()
        fun goToPrevious()
        fun quit()

    }

}