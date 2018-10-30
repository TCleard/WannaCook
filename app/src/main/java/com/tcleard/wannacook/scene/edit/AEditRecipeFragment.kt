package com.tcleard.wannacook.scene.edit

import android.os.Bundle
import android.view.View
import com.tcleard.wannacook.R
import com.tcleard.wannacook.core.extension.rx.sub
import com.tcleard.wannacook.core.model.Recipe
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.core.presenter.IView
import com.tcleard.wannacook.ui.controller.AFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class AEditRecipeFragment<P : AEditRecipeFragment.Presenter<*>> : AFragment<P>() {

    var isSelected = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    onSelected()
                }
            }
        }

    var recipe: Recipe? = null
    lateinit var leftButton: LeftButton
    lateinit var rightButton: RightButton

    protected val statePublisher = PublishSubject.create<Boolean>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachPresenter()
        if (isSelected) {
            presenter.onSelected()
        }
    }

    abstract fun attachPresenter()

    fun watchState(): Observable<Boolean> =
            Observable.create { e ->
                statePublisher.sub(
                        onNext = { e.onNext(it) }
                )
            }

    protected fun onSelected() {
        if (isPresenterReady) {
            presenter.onSelected()
        }
    }

    abstract class Presenter<V : IView> : APresenter<V>() {

        abstract fun onSelected()

    }

    enum class LeftButton(val res: Int) {
        QUIT(R.string.quit),
        BACK(R.string.back)
    }

    enum class RightButton(val res: Int) {
        SEND(R.string.send),
        NEXT(R.string.next)
    }

}