package com.example.zhaoy.eyepetizer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zhaoy.eyepetizer.net.IApi;
import com.example.zhaoy.eyepetizer.net.RetrofitFactory;
import com.example.zhaoy.eyepetizer.utils.DisplayManager;


public class MyApplication extends Application {

    private static Context context;
    private static AppCompatActivity mActivity;
    public static IApi iApi;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                    mActivity = (AppCompatActivity) activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                    mActivity = (AppCompatActivity) activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                mActivity = null;
            }

            @Override
            public void onActivityStopped(Activity activity) {
                mActivity = null;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                mActivity = null;
            }
        });

        iApi = RetrofitFactory.INSTANCE.getRetrofitGsonService();

        DisplayManager.INSTANCE.init(this);
    }

    public static Context getContext(){
        return context;
    }

    public static AppCompatActivity getActivity(){
        return mActivity;
    }
}
