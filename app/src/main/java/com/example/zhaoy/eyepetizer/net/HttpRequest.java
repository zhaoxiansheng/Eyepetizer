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
     * @param isShow 是否展示请求弹窗
     */
    public static void getCategories(boolean isShow){
        Observable<ArrayList<ResponseClasses.Categories>> categories = MyApplication.iApi.getCategories();
        RetrofitFactory.INSTANCE.doHttpRequest(categories, new BaseObserver<ArrayList<ResponseClasses.Categories>>(MyApplication.getContext(), isShow) {
            @Override
            protected void onSuccess(ArrayList<ResponseClasses.Categories> categories) {
                for (ResponseClasses.Categories c: categories) {
                    System.out.println(c.getName());
                }
            }
        });
    }
}
