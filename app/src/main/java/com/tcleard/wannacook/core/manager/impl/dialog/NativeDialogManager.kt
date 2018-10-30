package com.tcleard.wannacook.core.manager.impl.dialog

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.view.ContextThemeWrapper
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.manager.IDialogManager

class NativeDialogManager : IDialogManager {

    override fun builder(activity: Activity): IDialogManager.Builder = Builder(activity)

    class Builder(context: Context) : IDialogManager.Builder(context) {

        override fun build(): IDialogManager.Dialog {
            lateinit var dialog: Dialog
            var nativeBuilder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.DialogStyle))
                    .setCancelable(isCancelable)
                    .setTitle(title)
                    .setMessage(message)
            view?.let {
                nativeBuilder = nativeBuilder.setView(view)
            }
            if (positiveButton?.isNotBlank() == true) {
                nativeBuilder = nativeBuilder.setPositiveButton(positiveButton) { _, _ -> onPositiveClickListener.invoke(dialog) }
            }
            if (neutralButton?.isNotBlank() == true) {
                nativeBuilder = nativeBuilder.setNeutralButton(neutralButton) { _, _ -> onNeutralClickListener.invoke(dialog) }
            }
            if (negativeButton?.isNotBlank() == true) {
                nativeBuilder = nativeBuilder.setNegativeButton(negativeButton) { _, _ -> onNegativeClickListener.invoke(dialog) }
            }
            dialog = Dialog(nativeBuilder)
            return dialog
        }

    }

    class Dialog(
            builder: AlertDialog.Builder
    ) : IDialogManager.Dialog {

        private val nativeDialog = builder.create()

        override fun show() {
            nativeDialog.show()
        }

        override fun dismiss() {
            nativeDialog.dismiss()
        }

    }

}