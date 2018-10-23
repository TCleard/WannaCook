package com.tcleard.wannacook.core.extension.rx

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun Completable.onIoToMain(): Completable =
        subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

fun Completable.sub(
        onSuccess: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null): Disposable =
        subscribe({
            onSuccess?.invoke()
        }, {
            it.printStackTrace()
            onError?.invoke(it)
        })