package com.tcleard.wannacook.core.manager.impl

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import com.tcleard.wannacook.core.manager.IKeyboardManager
import com.tcleard.wannacook.ui.controller.AActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class KeyboardManager : IKeyboardManager, ViewTreeObserver.OnGlobalLayoutListener {

    private var isKeyboardOpen = false
        set(value) {
            if (field != value) {
                field = value
                keyboardStateListener.onNext(value)
            }
        }

    private var activity: Activity? = null
    private var rootView: View? = null

    private val imm: InputMethodManager?
        get() = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

    private val keyboardStateListener = PublishSubject.create<Boolean>()

    override fun updateCurrentActivity(activity: AActivity<*>?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            rootView?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
        } else {
            rootView?.viewTreeObserver?.removeGlobalOnLayoutListener(this)
        }
        this.activity = activity
        rootView = activity?.window?.decorView?.findViewById(android.R.id.content)
        rootView?.viewTreeObserver?.addOnGlobalLayoutListener(this)
    }

    override fun openKeyboard(view: View) {
        view.requestFocus()
        imm?.showSoftInput(view, 0)
    }

    override fun closeKeyboard(view: View) {
        view.clearFocus()
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun closeKeyboard() {
        activity?.currentFocus?.let {
            closeKeyboard(it)
        }
    }

    override fun isKeyboardOpen(): Boolean = isKeyboardOpen

    override fun listenKeyboardStateChanged(): Observable<Boolean> = keyboardStateListener

    override fun onGlobalLayout() {
        val r = Rect()
        rootView?.getWindowVisibleDisplayFrame(r)
        val screenHeight = rootView?.rootView?.height ?: 0

        // r.bottom is the position above soft keypad or device button.
        // if keypad is shown, the r.bottom is smaller than that before.
        val keypadHeight = screenHeight - r.bottom

        isKeyboardOpen = keypadHeight > screenHeight * 0.15
    }

}