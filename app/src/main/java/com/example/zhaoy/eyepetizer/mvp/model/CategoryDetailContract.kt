package com.example.zhaoy.eyepetizer.mvp.model

import com.example.zhaoy.eyepetizer.bean.Item
import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import com.example.zhaoy.eyepetizer.mvp.base.BasePresenter
import com.example.zhaoy.eyepetizer.mvp.base.BaseView

interface CategoryDetailContract {
    interface IView : BaseView<IPresenter> {
        fun setHeader(category: ResponseClasses.Categories)
        fun setListData(itemList: ArrayList<Item>)
        fun onError()
    }

    interface IPresenter : BasePresenter {
        fun requestMoreData()

        fun start(category: ResponseClasses.Categories)
    }
}