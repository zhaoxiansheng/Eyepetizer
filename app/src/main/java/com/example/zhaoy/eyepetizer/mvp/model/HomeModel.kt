package com.example.zhaoy.eyepetizer.mvp.model

import com.example.zhaoy.eyepetizer.bean.HomeBean
import com.example.zhaoy.eyepetizer.net.HttpRequest
import io.reactivex.Observable

class HomeModel {

    fun loadFirstData(): Observable<HomeBean> {
        return HttpRequest.getFirstHomeData(System.currentTimeMillis())
    }

    fun loadMoreData(url: String): Observable<HomeBean> {
        return HttpRequest.getMoreHomeData(url)
    }
}