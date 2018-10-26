package com.tcleard.wannacook.core.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.watch(
        beforeTextChanged: (() -> Unit)? = null,
        onTextChanged: (() -> Unit)? = null,
        afterTextChanged: (() -> Unit)? = null
) {
    addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            afterTextChanged?.invoke()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChanged?.invoke()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged?.invoke()
        }

    })
}