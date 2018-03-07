package com.example.zhaoy.eyepetizer.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.*
import com.example.zhaoy.eyepetizer.R
import com.example.zhaoy.eyepetizer.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Created by zhaoy on 2018/3/7.
 */
class WebViewActivity : BaseActivity() {

    companion object {
        val URL: String = "file:///android_asset/region.html"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        initWebView()
    }

    @SuppressLint("JavascriptInterface")
    private fun initWebView() {
        //缩放至屏幕大小
        web_view.settings.loadWithOverviewMode = true

        //可读取本地json文件
        web_view.settings.allowUniversalAccessFromFileURLs = true

        //设置编码格式
        web_view.settings.defaultTextEncodingName = "utf-8"

        web_view.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        //开启脚本支持
        web_view.settings.javaScriptEnabled = true

        web_view.settings.javaScriptCanOpenWindowsAutomatically = true
        //开启本地文件读取 默认为true
        web_view.settings.allowFileAccess = true
        web_view.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        web_view.settings.setSupportZoom(true)
        //设置缩放工具
        web_view.settings.builtInZoomControls = true
        //进行WebView自适应屏幕 将图片调整到适合WebView的大小
        web_view.settings.useWideViewPort = true
        web_view.settings.setSupportMultipleWindows(true)
        web_view.settings.setAppCacheEnabled(true)
        web_view.settings.domStorageEnabled = true
        web_view.settings.setGeolocationEnabled(true)
        web_view.settings.setAppCacheMaxSize(Long.MAX_VALUE)
        web_view.settings.pluginState = WebSettings.PluginState.ON_DEMAND
        web_view.settings.cacheMode = WebSettings.LOAD_NO_CACHE


        //设置背景颜色
        web_view.setBackgroundColor(0)
        web_view.isScrollContainer = false

        web_view.addJavascriptInterface(WebViewActivity(), "h5")
        WebView.setWebContentsDebuggingEnabled(true)

        web_view.loadUrl("https://www.baidu.com")

        web_view.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        })

        //js调用加上这个就好了
        web_view.setWebChromeClient(object : WebChromeClient() {
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                return super.onJsAlert(view, url, message, result)
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }
        })
    }
}
