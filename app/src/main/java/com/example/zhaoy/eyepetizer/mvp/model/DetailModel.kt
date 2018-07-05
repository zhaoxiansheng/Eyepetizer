package com.example.zhaoy.eyepetizer.mvp.model

import com.example.zhaoy.eyepetizer.bean.Issue
import com.example.zhaoy.eyepetizer.net.HttpRequest
import io.reactivex.Observable

/**
 * 详情页Model，请求默认精选（无date是banner，每次加载更多，加载一条带date）
 */
class DetailModel {

    fun loadRelatedData(id: Long): Observable<Issue> {
        return HttpRequest.getRelatedData(id)
    }

    fun loadDetailMoreRelatedList(url: String): Observable<Issue> {
        return HttpRequest.getIssue(url)
    }

    fun loadReplyList(videoId: Long): Observable<Issue> {
        return HttpRequest.getReply(videoId)
    }

    fun loadMoreReplyList(url: String): Observable<Issue> {
        return HttpRequest.getIssue(url)
    }
}