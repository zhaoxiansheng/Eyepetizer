package com.example.zhaoy.eyepetizer.mvp.model

import com.example.zhaoy.eyepetizer.bean.Issue
import com.example.zhaoy.eyepetizer.net.HttpRequest
import io.reactivex.Observable

class CategoryDetailModel {

    fun loadData(id: Int): Observable<Issue> {
        return HttpRequest.getCategoriesItemList(id)
    }

    fun loadMoreData(url: String): Observable<Issue> {
        return HttpRequest.getIssue(url)
    }
}