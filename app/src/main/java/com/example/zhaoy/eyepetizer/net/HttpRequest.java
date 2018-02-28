package com.example.zhaoy.eyepetizer.net;

import com.example.zhaoy.eyepetizer.MyApplication;
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
    public static Observable<ArrayList<ResponseClasses.Categories>> getCategories(){
        Observable<ArrayList<ResponseClasses.Categories>> categories = MyApplication.iApi.getCategories();
        return RetrofitFactory.INSTANCE.doHttpRequest(categories);
    }
}
