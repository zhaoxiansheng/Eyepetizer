package com.example.zhaoy.eyepetizer.mvp.presenter

import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import com.example.zhaoy.eyepetizer.mvp.model.CategoryDetailContract
import com.example.zhaoy.eyepetizer.mvp.model.CategoryDetailModel

class CategoryDetailPresenter(view: CategoryDetailContract.IView) : CategoryDetailContract.IPresenter {


    private val categoryView: CategoryDetailContract.IView = view

    var nextPageUrl = ""

    override fun requestMoreData() {
        categoryDetailModel.loadMoreData(nextPageUrl)
                .subscribe({ issue ->
                    nextPageUrl = issue.nextPageUrl
                    categoryView.setListData(issue.itemList)
                }, {
                    it.printStackTrace()
                    categoryView.onError()
                })
    }

    override fun start(category: ResponseClasses.Categories) {
        categoryView.setHeader(category)
        categoryDetailModel.loadData(category.id)
                .subscribe({ issue ->
                    nextPageUrl = issue.nextPageUrl
                    categoryView.setListData(issue.itemList)
                }, {
                    it.printStackTrace()
                    categoryView.onError()
                })
    }


    val categoryDetailModel: CategoryDetailModel by lazy {
        CategoryDetailModel()
    }

}