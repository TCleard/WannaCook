package com.tcleard.wannacook.core.extension.rx

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.onIoToMain(): Single<T> =
        subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.sub(
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null): Disposable =
        subscribe({
            onSuccess?.invoke(it)
        }, {
            it.printStackTrace()
            onError?.invoke(it)
        })