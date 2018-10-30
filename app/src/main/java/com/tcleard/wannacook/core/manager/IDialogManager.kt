package com.tcleard.wannacook.core.manager

import android.app.Activity
import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.view.View

interface IDialogManager {

    fun builder(activity: Activity): Builder

    interface Dialog {

        fun show()
        fun dismiss()

    }

    abstract class Builder(
            protected val context: Context
    ) {

        var isCancelable = true

        var title: String? = null
        var message: String? = null

        var view: View? = null

        var positiveButton: String? = null
        var neutralButton: String? = null
        var negativeButton: String? = null

        var onPositiveClickListener: (Dialog) -> Unit = {}
        var onNeutralClickListener: (Dialog) -> Unit = {}
        var onNegativeClickListener: (Dialog) -> Unit = {}

        private fun getString(@StringRes stringRes: Int?): String? = stringRes?.let {
            context.getString(it)
        }

        open fun setCancelable(isCancelable: Boolean): Builder {
            this.isCancelable = isCancelable
            return this
        }

        open fun setTitle(title: String?): Builder {
            this.title = title
            return this
        }

        fun setTitle(@StringRes titleRes: Int): Builder =
                setTitle(getString(titleRes))

        open fun setMessage(message: String?): Builder {
            this.message = message
            return this
        }

        fun setMessage(@StringRes messageRes: Int): Builder =
                setMessage(getString(messageRes))

        fun setView(view: View?): Builder {
            this.view = view
            return this
        }

        open fun setPositiveButton(positiveButton: String?, onPositiveClickListener: (Dialog) -> Unit): Builder {
            this.positiveButton = positiveButton
            this.onPositiveClickListener = onPositiveClickListener
            return this
        }

        fun setPositiveButton(positiveButtonRes: Int?, onPositiveClickListener: (Dialog) -> Unit): Builder =
                setPositiveButton(getString(positiveButtonRes), onPositiveClickListener)

        open fun setNeutralButton(negativeButton: String?, onNeutralClickListener: (Dialog) -> Unit): Builder {
            this.neutralButton = neutralButton
            this.onNeutralClickListener = onNeutralClickListener
            return this
        }

        fun setNeutralButton(neutralButtonRes: Int?, onNeutralClickListener: (Dialog) -> Unit): Builder =
                setNeutralButton(getString(neutralButtonRes), onNeutralClickListener)

        open fun setNegativeButton(negativeButton: String?, onNegativeClickListener: (Dialog) -> Unit): Builder {
            this.negativeButton = negativeButton
            this.onNegativeClickListener = onNegativeClickListener
            return this
        }

        fun setNegativeButton(negativeButtonRes: Int?, onNegativeClickListener: (Dialog) -> Unit): Builder =
                setNegativeButton(getString(negativeButtonRes), onNegativeClickListener)

        abstract fun build(): Dialog

    }

}