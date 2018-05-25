package com.example.zhaoy.eyepetizer.ui.view.common

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class JumpShowTextView : FrameLayout {

    private val allTime = 700

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private var placeHolder: PercentTextView? = null

    private var realTextView: PercentTextView? = null

    private var withAnimation = true

    var text: String? = ""
        set(value) {
            reCreateObject()
            init()
            field = value
            placeHolder?.text = value
            start()
        }

    private fun reCreateObject() {
        placeHolder = PercentTextView(context)
        placeHolder?.textSize = textSize
        placeHolder?.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        placeHolder?.visibility = View.INVISIBLE

        realTextView = PercentTextView(context)
        realTextView?.setTextColor(color)
        realTextView?.textSize = textSize
        realTextView?.paint?.isFakeBoldText = isBold
        realTextView?.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        if (singline) {
            placeHolder?.setSingleLine(singline)
            placeHolder?.ellipsize = TextUtils.TruncateAt.END
            realTextView?.ellipsize = TextUtils.TruncateAt.END
            realTextView?.setSingleLine(singline)
        }

    }

    private fun init() {
        removeAllViews()
        addView(placeHolder)
        addView(realTextView)
    }


    var content: String? = ""

    var isBold: Boolean = false
    var color: Int = Color.BLACK
    private var singline = false

    var textSize: Float = 52F

    //线程正在运行
    private var isRun: Boolean = false

    var marginBottom: Float = 0f


    private var subscribe: Disposable? = null

    fun start() {
        if (withAnimation) {
            if (isRun) {
                subscribe?.dispose()
            }
            content = ""

            isRun = true
            text?.let {

                if (it.isNotEmpty()) {
                    val intervalTime = allTime / it.length
                    subscribe = Observable.interval(intervalTime.toLong(), TimeUnit.MILLISECONDS)
                            .take(it.length.toLong())
                            .subscribeOn(Schedulers.io())
                            .unsubscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ i ->
                                content += it[i.toInt()]
                                realTextView?.text = content
                            }, { e -> e.printStackTrace() }, { isRun = false }
                            )
                }

            }
        } else {
            realTextView?.text = text
        }
    }
}