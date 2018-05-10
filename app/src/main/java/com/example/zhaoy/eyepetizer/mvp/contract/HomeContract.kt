package com.example.zhaoy.eyepetizer.mvp.contract

import com.example.zhaoy.eyepetizer.bean.HomeBean
import com.example.zhaoy.eyepetizer.bean.Item
import com.example.zhaoy.eyepetizer.mvp.base.BasePresenter
import com.example.zhaoy.eyepetizer.mvp.base.BaseView

/**
 * 首页的契约接口，统一管理view和presenter中的接口，使得二者的功能一目了然
 * Created by zhaoy on 2018/5/9.
 */
interface HomeContract {

    interface IView : BaseView<IPresenter> {
        fun setFirstData(homeBean: HomeBean)
        fun setMoreData(itemList:ArrayList<Item>)
        fun onError()
    }

    interface IPresenter : BasePresenter {
        /**
         * 刷新数据、第一次请求你数据
         */
        fun requestFirstData()

        /**
         * 底部加载更多
         */
        fun requestMoreData()
    }
}