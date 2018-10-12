package com.tcleard.wannacook.ui.controller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.tcleard.wannacook.App
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.di.component.AppComponent
import javax.inject.Inject

abstract class AFragment<P : APresenter<*>> : Fragment() {

    @Inject
    protected lateinit var presenter: P

    val isViewCreated: Boolean
        get() = view != null

    val appComponent: AppComponent
        get() = (activity?.application as App).component

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

}