package com.example.zhaoy.eyepetizer.ui.activity

import android.os.Bundle
import com.example.zhaoy.eyepetizer.R
import com.example.zhaoy.eyepetizer.ui.base.BaseActivity
/**
 * Created by zhaoy on 2018/3/7.
 */
class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initView()
    }

    private fun initView(){
    }
}
