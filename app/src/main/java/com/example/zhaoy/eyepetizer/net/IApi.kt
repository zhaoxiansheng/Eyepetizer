package com.example.zhaoy.eyepetizer.net

import com.example.zhaoy.eyepetizer.bean.HomeBean
import com.example.zhaoy.eyepetizer.bean.Issue
import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.ArrayList

interface IApi {

    @GET("v4/categories/")
    fun getCategories(): Observable<ArrayList<ResponseClasses.Categories>>

    /**
     * banner+一页数据，num=1
     */
    @GET("v2/feed?&num=1")
    fun getFirstHomeData(@Query("date") date: Long): Observable<HomeBean>

    /**
     * 根据next page url请求数据
     */
    @GET
    fun getMoreHomeData(@Url url: String): Observable<HomeBean>


    /**
     * issue里面包了itemlist和nextpageurl
     */
    @GET
    fun getIssue(@Url url: String): Observable<Issue>

    /**
     * 获取回复
     */
    @GET("v2/replies/video?")
    fun getReply(@Query("videoId") videoId: Long): Observable<Issue>

    /**
     * 根据item id获取相关视频
     */
    @GET("v4/video/related?")
    fun getRelatedData(@Query("id") id: Long): Observable<Issue>

    /**
     * 获取分类下的全部数据
     */
    @GET("v4/categories/videoList")
    fun getCategoryItemList(@Query("id") id: Long): Observable<Issue>
}