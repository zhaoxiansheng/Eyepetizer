package com.example.zhaoy.eyepetizer.net;

import com.example.zhaoy.eyepetizer.MyApplication;
import com.example.zhaoy.eyepetizer.bean.HomeBean;
import com.example.zhaoy.eyepetizer.bean.Issue;
import com.example.zhaoy.eyepetizer.bean.ResponseClasses;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * http请求类
 *
 * @author zhaoy
 */
public class HttpRequest {

    /**
     * 分类界面请求
     */
    public static Observable<ArrayList<ResponseClasses.Categories>> getCategories() {
        Observable<ArrayList<ResponseClasses.Categories>> categories = MyApplication.iApi.getCategories();
        return RetrofitFactory.INSTANCE.doHttpRequest(categories);
    }

    /**
     * 首页第一页
     */
    public static Observable<HomeBean> getFirstHomeData(long time) {
        Observable<HomeBean> firstHomeData = MyApplication.iApi.getFirstHomeData(time);
        return RetrofitFactory.INSTANCE.doHttpRequest(firstHomeData);
    }

    /**
     * 首页更多
     */
    public static Observable<HomeBean> getMoreHomeData(String url) {
        Observable<HomeBean> moreHomeData = MyApplication.iApi.getMoreHomeData(url);
        return RetrofitFactory.INSTANCE.doHttpRequest(moreHomeData);
    }

    /**
     * 获取分类下的全部数据
     */
    public static Observable<Issue> getCategoriesItemList(int id) {
        Observable<Issue> categoriesItemList = MyApplication.iApi.getCategoryItemList(id);
        return RetrofitFactory.INSTANCE.doHttpRequest(categoriesItemList);
    }

    /**
     * issue里面包了itemlist和nextpageurl
     */
    public static Observable<Issue> getIssue(String url) {
        Observable<Issue> issue = MyApplication.iApi.getIssue(url);
        return RetrofitFactory.INSTANCE.doHttpRequest(issue);
    }

    /**
     * 根据item id获取相关视频
     */
    public static Observable<Issue> getRelatedData(long id){
        Observable<Issue> relateData = MyApplication.iApi.getRelatedData(id);
        return RetrofitFactory.INSTANCE.doHttpRequest(relateData);
    }

    /**
     * 获取回复
     */
    public static Observable<Issue> getReply(long videoId){
        Observable<Issue> reply = MyApplication.iApi.getReply(videoId);
        return RetrofitFactory.INSTANCE.doHttpRequest(reply);
    }
}
