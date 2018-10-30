package com.tcleard.wannacook.scene.edit.title

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.extension.watch
import com.tcleard.wannacook.scene.edit.AEditRecipeFragment
import kotlinx.android.synthetic.main.dialog_color_picker.view.*
import kotlinx.android.synthetic.main.fragment_edit_title.*

class EditTitleFragment : AEditRecipeFragment<EditTitlePresenter>(), EditTitlePresenter.RecipeTitleView, View.OnClickListener {

    override fun onFirstCreate() {
        super.onFirstCreate()
        DaggerEditTitleComponent.builder()
                .appComponent(appComponent)
                .editTitleModule(EditTitleModule())
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_edit_title, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTitleField.watch(afterTextChanged = {
            presenter.setTitle(editTitleField.text.toString())
        })

        editTitleField.imeOptions = EditorInfo.IME_ACTION_DONE
        editTitleField.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)

        editTitleColorIndicator.setOnClickListener(this)

    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    /** RecipeTitleView **/

    override fun setState(state: Boolean) {
        statePublisher.onNext(state)
        editTitleFieldExtra.setTextColor(if (state) {
            resources.getColor(R.color.light)
        } else {
            resources.getColor(R.color.red)
        })
    }

    override fun showTitle(title: String) {
        editTitleField.setText(title)
    }

    override fun showColor(color: Int?) {
        editTitleColorIndicator.setImageDrawable(ColorDrawable(color
                ?: resources.getColor(R.color.colorAccent)))
    }

    override fun goToColorPicker(color: Int?) {
        val startColor = color ?: resources.getColor(R.color.colorAccent)
        val colorView = View.inflate(requireContext(), R.layout.dialog_color_picker, null)
        colorView.colorPickerStart.setImageDrawable(ColorDrawable(startColor))
        colorView.colorPickerView.setInitialColor(startColor)
        colorView.colorPickerView.subscribe { color, fromUser ->
            colorView.colorPickerEnd.setImageDrawable(ColorDrawable(color))
        }
        dialogManager.builder(requireActivity())
                .setView(colorView)
                .setPositiveButton(R.string.validate) { dialog ->
                    presenter.setColor(colorView.colorPickerView.color)
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.cancel) { dialog ->
                    dialog.dismiss()
                }
                .build()
                .show()
    }

    /** Listeners **/

    override fun onClick(v: View?) {
        when (v) {
            editTitleColorIndicator -> presenter.onColorClicked()
        }
    }
}