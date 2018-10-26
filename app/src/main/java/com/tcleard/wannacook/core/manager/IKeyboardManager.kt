package com.tcleard.wannacook.core.manager

import android.view.View
import com.tcleard.wannacook.ui.controller.AActivity
import io.reactivex.Observable

interface IKeyboardManager {

    fun updateCurrentActivity(activity: AActivity<*>?)

    fun openKeyboard(view: View)
    fun closeKeyboard(view: View)

    fun closeKeyboard()

    fun isKeyboardOpen(): Boolean

    fun listenKeyboardStateChanged(): Observable<Boolean>

}