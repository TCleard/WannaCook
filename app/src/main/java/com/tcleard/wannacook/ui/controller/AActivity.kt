package com.tcleard.wannacook.ui.controller

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.annotation.StringRes
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentTransaction
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import android.view.View
import com.tcleard.wannacook.App
import com.tcleard.wannacook.core.manager.IDialogManager
import com.tcleard.wannacook.core.manager.IImageManager
import com.tcleard.wannacook.core.manager.IKeyboardManager
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.di.component.AppComponent
import javax.inject.Inject


abstract class AActivity<P : APresenter<*>> : AppCompatActivity(), IController {

    @Inject
    protected lateinit var presenter: P

    @Inject
    protected lateinit var dialogManager: IDialogManager
    @Inject
    protected lateinit var imageManager: IImageManager
    @Inject
    protected lateinit var keyboardManager: IKeyboardManager

    val appComponent: AppComponent
        get() = (application as App).component

    override fun onResume() {
        super.onResume()
        keyboardManager.updateCurrentActivity(this)
    }

    override fun onPause() {
        super.onPause()
        keyboardManager.updateCurrentActivity(null)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    fun setLayoutBelowStatusBar(bool: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (bool) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // window.statusBarColor = resources.getColor(R.color.whiteAlpha)
            } else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE xor View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // val typedValue = TypedValue()
                // theme.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
                // window.statusBarColor = typedValue.data
            }
        }
    }

    fun fragmentTransaction(block: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        block.invoke(fragmentTransaction)
        fragmentTransaction.commit()
    }

    fun addExitListener(onExit: (() -> Unit)? = null, onReenter: (() -> Unit)? = null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.sharedElementExitTransition?.addListener(object : TransitionListener {

                override fun onTransitionStart(transition: Transition?) {
                    onExit?.invoke()
                }

                override fun onTransitionEnd(transition: Transition?) {
                    window.sharedElementExitTransition.removeListener(this)
                    window.sharedElementReenterTransition?.addListener(object : TransitionListener {

                        override fun onTransitionEnd(transition: Transition?) {
                            window.sharedElementReenterTransition.removeListener(this)
                            onReenter?.invoke()
                        }

                    })
                }

            })
        }
    }

    fun addEnterListener(beforeStart: (() -> Unit)? = null, onEnter: (() -> Unit)? = null, onReturn: (() -> Unit)? = null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            beforeStart?.invoke()
            window.sharedElementEnterTransition?.addListener(object : TransitionListener {

                override fun onTransitionEnd(transition: Transition?) {
                    onEnter?.invoke()
                    window.sharedElementEnterTransition.removeListener(this)
                    window.sharedElementReturnTransition?.addListener(object : TransitionListener {

                        override fun onTransitionStart(transition: Transition?) {
                            window.sharedElementReturnTransition.removeListener(this)
                            onReturn?.invoke()
                        }

                    })
                }

            })
        }
    }

    override fun provideContext(): Context = this

    abstract class Builder constructor(
            private val controller: IController
    ) {

        private val context: Context
            get() = controller.provideContext()

        protected val intent: Intent by lazy {
            Intent(context, activityClass)
        }

        abstract val activityClass: Class<*>

        private val transitions = arrayListOf<Pair<View, String>>()

        fun addSharedElement(view: View, @StringRes transitionNameRes: Int): Builder =
                addSharedElement(view, context.resources.getString(transitionNameRes))

        fun addSharedElement(view: View, transitionName: String): Builder {
            transitions.add(Pair(view, transitionName))
            return this
        }

        fun start() {
            if (context is Activity && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && transitions.isNotEmpty()) {
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitions.toTypedArray())
                controller.startActivity(intent, options.toBundle())
            } else {
                controller.startActivity(intent)
            }
        }

        fun startForResult(requestCode: Int) {
            controller.startActivityForResult(intent, requestCode)
        }

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    interface TransitionListener : Transition.TransitionListener {

        override fun onTransitionStart(transition: Transition?) {}

        override fun onTransitionEnd(transition: Transition?) {}

        override fun onTransitionResume(transition: Transition?) {}

        override fun onTransitionPause(transition: Transition?) {}

        override fun onTransitionCancel(transition: Transition?) {}
    }

}