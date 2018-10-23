package com.tcleard.wannacook.scene.edit.title

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.tcleard.wannacook.R
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import kotlinx.android.synthetic.main.fragment_recipe_title.*

class RecipeTitleFragment : AEditRecipeFragment<RecipeTitlePresenter>(), RecipeTitlePresenter.RecipeTitleView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerRecipeTitleComponent.builder()
                .appComponent(appComponent)
                .recipeTitleModule(RecipeTitleModule())
                .build()
                .inject(this)

        presenter.attach(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_recipe_title, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeTitleContent.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                presenter.setTitle(recipeTitleContent.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        recipeTitleContent.imeOptions = EditorInfo.IME_ACTION_DONE
        recipeTitleContent.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)

    }

    /** RecipeTitleView **/

    override fun setState(state: Boolean) {
        statePublisher.onNext(state)
        recipeTitleExtra.setTextColor(if (state) {
            resources.getColor(R.color.light)
        } else {
            resources.getColor(R.color.red)
        })
    }

    override fun showTitle(title: String) {
        recipeTitleContent.setText(title)
    }
}