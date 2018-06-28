package com.example.zhaoy.eyepetizer;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.zhaoy.eyepetizer.net.IApi;
import com.example.zhaoy.eyepetizer.net.RetrofitFactory;
import com.example.zhaoy.eyepetizer.utils.DisplayManager;

import java.lang.ref.WeakReference;


public class MyApplication extends Application {

    private static WeakReference<Context> context;
    private static WeakReference<AppCompatActivity> mActivity;
    public static IApi iApi;

    @Override
    public void onCreate() {
        super.onCreate();
        context = new WeakReference<>(getApplicationContext());

        iApi = RetrofitFactory.INSTANCE.getRetrofitGsonService();

        DisplayManager.INSTANCE.init(this);
    }

    public static Context getContext() {
        return context.get();
    }

    public static AppCompatActivity getActivity() {
        return mActivity.get();
    }
}
