package com.example.zhaoy.eyepetizer.ui.base

import android.support.v4.app.Fragment
import com.example.zhaoy.eyepetizer.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

var currentFragment = R.id.rb_home
val tabsId = listOf(R.id.rb_home, R.id.rb_category, R.id.rb_hot)

abstract class BaseFragment(tabId: Int) : Fragment(), RxNetManager {
    var tabId = 0

    init {
        this.tabId = tabId
    }

    protected val disposables = CompositeDisposable()

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    override fun dispose(disposable: Disposable) {
        disposables.remove(disposable)
    }

    override fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    open fun setupToolbar(): Boolean {
        //解决MainActivity fragment切换时，toolbar更新bug（homefragment中recyclerview滚动会更新toolbar，如果不控制，在滚动过程中切换了tab，toolbar会依旧被更新）
        if (tabId != currentFragment) {
            return true
        }
        return false
    }

}
