package com.example.zhaoy.eyepetizer.ui.view.home.banner

import android.support.v4.view.ViewPager
import android.view.View

class HomeBannerTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val width: Int = page.width
        //todo 2018/06/04 position不太理解
        println("position:" + position)
        //以向左滑动为例
//        if (position <= 0) {//中间的
        page.scrollX = (position * width).toInt() / 4 * 3
//        } else if (position <= 1) {//右侧的
//            page?.scrollX = (position * width).toInt() / 4 * 3

//        }
    }
}