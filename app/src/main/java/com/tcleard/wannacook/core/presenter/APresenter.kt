package com.tcleard.wannacook.core.presenter

import io.reactivex.disposables.CompositeDisposable

abstract class APresenter<V : IView> {

    protected var view: V? = null

    protected val compositeDisposable = CompositeDisposable()

    open fun attach(view: V) {
        this.view = view
    }

    fun unbind() {
        view = null
        compositeDisposable.clear()
    }

}