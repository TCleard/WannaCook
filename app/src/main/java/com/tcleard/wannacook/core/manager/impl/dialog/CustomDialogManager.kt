package com.tcleard.wannacook.core.manager.impl.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.manager.IDialogManager
import kotlinx.android.synthetic.main.widget_dialog.*

class CustomDialogManager : IDialogManager {

    override fun builder(activity: AppCompatActivity): IDialogManager.Builder = Builder(activity)

    class Builder(context: Context) : IDialogManager.Builder(context) {

        override fun build(): IDialogManager.Dialog = CustomDialog(context, this)

    }

    class CustomDialog(
            context: Context,
            private val builder: Builder
    ) : Dialog(context, R.style.DialogStyle), IDialogManager.Dialog {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.widget_dialog)
            setCancelable(builder.isCancelable)

            if (builder.title?.isNotBlank() == true) {
                dialogTitle.text = builder.title
            } else {
                dialogTitle.visibility = View.GONE
            }

            if (builder.message?.isNotBlank() == true) {
                dialogMessage.text = builder.message
            } else {
                dialogMessage.visibility = View.GONE
            }

            if (builder.positiveButton?.isNotBlank() == true) {
                dialogPositive.text = builder.positiveButton
                dialogPositive.setOnClickListener { builder.onPositiveClickListener.invoke(this) }
            } else {
                dialogPositive.visibility = View.GONE
            }

            if (builder.neutralButton?.isNotBlank() == true) {
                dialogNeutral.text = builder.neutralButton
                dialogNeutral.setOnClickListener { builder.onNeutralClickListener.invoke(this) }
            } else {
                dialogNeutral.visibility = View.GONE
            }

            if (builder.negativeButton?.isNotBlank() == true) {
                dialogNegative.text = builder.negativeButton
                dialogNegative.setOnClickListener { builder.onNegativeClickListener.invoke(this) }
            } else {
                dialogNegative.visibility = View.GONE
            }

        }

    }

}