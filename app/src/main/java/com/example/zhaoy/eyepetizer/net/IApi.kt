package com.example.zhaoy.eyepetizer.net

import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.ArrayList

interface IApi {

    @GET("categories/")
    fun getCategories() : Observable<ArrayList<ResponseClasses.Categories>>
}