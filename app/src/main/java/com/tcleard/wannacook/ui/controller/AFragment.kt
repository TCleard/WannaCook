package com.tcleard.wannacook.ui.controller

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import com.tcleard.wannacook.App
import com.tcleard.wannacook.core.manager.IDialogManager
import com.tcleard.wannacook.core.manager.IKeyboardManager
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.di.component.AppComponent
import javax.inject.Inject

abstract class AFragment<P : APresenter<*>> : Fragment(), IController {

    @Inject
    protected lateinit var presenter: P

    @Inject
    protected lateinit var dialogManager: IDialogManager

    @Inject
    protected lateinit var keyboardManager: IKeyboardManager

    val isPresenterReady: Boolean
        get() = ::presenter.isInitialized

    private var isFirstCreation = true
    val isViewCreated: Boolean
        get() = view != null

    val appComponent: AppComponent
        get() = (activity?.application as App).component

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isFirstCreation) {
            isFirstCreation = false
            onFirstCreate()
        } else {
            onRecreate(savedInstanceState)
        }
    }

    open fun onFirstCreate() {

    }

    open fun onRecreate(savedData: Bundle?) {

    }
    
    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun provideContext(): Context = requireContext()

}