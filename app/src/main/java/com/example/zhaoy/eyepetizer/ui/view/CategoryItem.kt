package com.example.zhaoy.eyepetizer.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.example.zhaoy.eyepetizer.R
import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import kotlinx.android.synthetic.main.layout_category_item.view.*

/**
 * Created by zhaoy on 2018/2/28.
 */
class CategoryItem : FrameLayout {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.layout_category_item, this)
    }

    fun setData(category: ResponseClasses.Categories) {
        tv_name.text = "#"+category.name
        Glide.with(context).load(category.bgPicture).centerCrop().into(iv_category)
    }
}