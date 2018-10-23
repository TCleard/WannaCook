package com.tcleard.wannacook.core.service

import com.tcleard.wannacook.core.extension.rx.sub
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class AService<T> {

    private val publisher = PublishSubject.create<Watching<T>>()

    open fun watch(): Observable<Watching<T>> =
            Observable.create { e ->
                publisher.sub(
                        onNext = {
                            e.onNext(it)
                        },
                        onComplete = {
                            e.onComplete()
                        }
                )
            }

    fun notifyAddition(item: T) {
        publisher.onNext(Watching(Watching.Action.ADD, item))
    }

    fun notifyUpdate(item: T) {
        publisher.onNext(Watching(Watching.Action.UPDATE, item))
    }

    fun notifyDeletion(item: T) {
        publisher.onNext(Watching(Watching.Action.DELETE, item))
    }

}