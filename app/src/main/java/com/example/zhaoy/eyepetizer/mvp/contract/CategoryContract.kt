package com.example.zhaoy.eyepetizer.mvp.contract

import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import com.example.zhaoy.eyepetizer.mvp.base.BasePresenter
import com.example.zhaoy.eyepetizer.mvp.base.BaseView
import java.util.ArrayList

interface CategoryContract {
    interface IView : BaseView<IPresenter> {
        fun showCategory(categories: ArrayList<ResponseClasses.Categories>)
    }

    interface IPresenter : BasePresenter {
        fun requestData()
    }
}