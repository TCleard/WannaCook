package com.tcleard.wannacook.core.extension.rx

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.onIoToMain(): Observable<T> =
        subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.sub(
        onNext: ((T) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null): Disposable =
        subscribe({
            onNext?.invoke(it)
        }, {
            it.printStackTrace()
            onError?.invoke(it)
        }, {
            onComplete?.invoke()
        })