package com.example.zhaoy.eyepetizer.net;

import com.example.zhaoy.eyepetizer.MyApplication;
import com.example.zhaoy.eyepetizer.bean.HomeBean;
import com.example.zhaoy.eyepetizer.bean.ResponseClasses;

import java.util.ArrayList;
import java.util.Date;

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
    public static Observable<ArrayList<ResponseClasses.Categories>> getCategories(){
        Observable<ArrayList<ResponseClasses.Categories>> categories = MyApplication.iApi.getCategories();
        return RetrofitFactory.INSTANCE.doHttpRequest(categories);
    }

    /**
     * 首页第一页
     */
    public static Observable<HomeBean> getFirstHomeData(long time){
        Observable<HomeBean> firstHomeData = MyApplication.iApi.getFirstHomeData(time);
        return RetrofitFactory.INSTANCE.doHttpRequest(firstHomeData);
    }

    /**
     * 首页更多
     */
    public static Observable<HomeBean> getMoreHomeData(String url){
        Observable<HomeBean> moreHomeData = MyApplication.iApi.getMoreHomeData(url);
        return RetrofitFactory.INSTANCE.doHttpRequest(moreHomeData);
    }
}
