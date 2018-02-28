package com.example.zhaoy.eyepetizer.mvp.presenter

import com.example.zhaoy.eyepetizer.MyApplication
import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import com.example.zhaoy.eyepetizer.mvp.contract.CategoryContract
import com.example.zhaoy.eyepetizer.mvp.model.CategoryModel
import com.example.zhaoy.eyepetizer.net.BaseObserver
import java.util.ArrayList

class CategoryPresenter(view: CategoryContract.IView) : CategoryContract.IPresenter{

    val categoryView: CategoryContract.IView

    val categoryModel: CategoryModel by lazy {
        CategoryModel()
    }

    init {
        categoryView = view
    }

    override fun requestData() {

        categoryModel.getCategories().subscribe(object : BaseObserver<ArrayList<ResponseClasses.Categories>>(MyApplication.getContext(), false) {
            override fun onSuccess(categories: ArrayList<ResponseClasses.Categories>) {
                categoryView.showCategory(categories)
            }
        })
    }
}