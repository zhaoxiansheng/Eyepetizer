package com.example.zhaoy.eyepetizer.mvp.model

import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import com.example.zhaoy.eyepetizer.net.HttpRequest
import io.reactivex.Observable
import java.util.*

class CategoryModel {

    fun getCategories(): Observable<ArrayList<ResponseClasses.Categories>> {
        return HttpRequest.getCategories()
    }
}
