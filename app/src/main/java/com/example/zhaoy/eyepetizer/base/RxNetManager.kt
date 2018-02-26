package com.example.zhaoy.eyepetizer.base

import io.reactivex.disposables.Disposable

interface RxNetManager {

    fun dispose(disposable: Disposable)
    fun addDisposable(disposable: Disposable)
}