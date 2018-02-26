package com.example.zhaoy.eyepetizer.net

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import com.example.zhaoy.eyepetizer.utils.NetworkUtils

import java.net.ConnectException
import java.net.SocketTimeoutException

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created on 2018/2/25.
 * 封装
 *
 * @author zhaoy
 */

abstract class BaseObserver<T> protected constructor(private val mContext: Context?, private val isShow: Boolean) : Observer<T> {
    private var progressDialog: ProgressDialog? = null

    init {
        if (isShow) {
            initProgressDialog()
        }
    }

    /**
     * 请求成功时 回掉函数
     *
     * @param t 请求成功时返回的结果
     */
    protected abstract fun onSuccess(t: T)

    override fun onSubscribe(d: Disposable) {
        showProgressDialog()
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    //同一做失败处理，必须实现
    override fun onError(e: Throwable) {
        errorDo(e)
        dismissProgressDialog()
    }

    override fun onComplete() {
        dismissProgressDialog()
    }

    private fun initProgressDialog() {
        if (progressDialog == null && mContext != null) {
            progressDialog = ProgressDialog(mContext)
            progressDialog!!.setCancelable(false)
        }
    }

    private fun showProgressDialog() {
        if (!isShow) {
            return
        }
        if (progressDialog == null || mContext == null) {
            return
        }
        if (!progressDialog!!.isShowing) {
            progressDialog!!.show()
        }
    }

    private fun dismissProgressDialog() {
        if (!isShow) {
            return
        }
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    private fun errorDo(e: Throwable) {
        if (mContext == null) {
            return
        }
        if (e is SocketTimeoutException) {
            Toast.makeText(mContext, "网络中断，请检查你的网络", Toast.LENGTH_SHORT).show()
            NetworkUtils.showWifiDlg(mContext)
        } else if (e is ConnectException) {
            Toast.makeText(mContext, "网络中断，请检查你的网络", Toast.LENGTH_SHORT).show()
            NetworkUtils.showWifiDlg(mContext)
        } else {
            Toast.makeText(mContext, "错误" + e.message, Toast.LENGTH_SHORT).show()
        }
    }
}