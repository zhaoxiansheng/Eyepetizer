package com.example.zhaoy.eyepetizer.ui.view.category

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
        val s = StringBuilder()
        s.append("#")
        s.append(category.name)
        tv_name.text = s
        Glide.with(context)
                .load(category.bgPicture)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .dontTransform()
                .into(iv_category)
    }
}
