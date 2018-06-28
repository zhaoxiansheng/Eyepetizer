package com.example.zhaoy.eyepetizer.ui.activity

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.zhaoy.eyepetizer.R
import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import com.example.zhaoy.eyepetizer.ui.base.BaseActivity
/**
 * Created by zhaoy on 2018/3/7.
 */
class CategoryDetialActivity : BaseActivity() {


    val adapter by lazy { CategoryDetailAdapter() }
    lateinit var categoryDetailPresenter: CategoryDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val category = intent.getSerializableExtra("data") as ResponseClasses.Categories
        setContentView(R.layout.activity_category_detail)
        categoryDetailPresenter = CategoryDetailPresenter(this)
        initView()
        categoryDetailPresenter.start(category)
    }

    fun initView() {
        rv_category_detail.layoutManager = LinearLayoutManager(this)
        rv_category_detail.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        rv_category_detail.adapter = adapter
        rv_category_detail.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                val position = parent.getChildPosition(view)
                val offset = DisplayManager.dip2px(10f)!!
                if ((position == 0)) {
                    outRect.set(0, offset, 0, 0)
                }
            }
        })


        rv_category_detail.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val childCount = rv_category_detail.getChildCount()
                    val itemCount = rv_category_detail.layoutManager.getItemCount()
                    val firstVisibleItem = (rv_category_detail.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {
                        Log.d(TAG, "到底了");
                        if (!loadingMore) {
                            loadingMore = true
                            onLoadMore()
                        }
                    }
                }
            }
        })
    }

    var loadingMore = false
    fun onLoadMore() {
        categoryDetailPresenter.requestMoreData()
    }

    override fun setHeader(category: Category) {
        category_header.setData(category)
    }

    override fun setListData(itemList: ArrayList<Item>) {
        loadingMore = false
        adapter.addData(itemList)
    }

    override fun onError() {
        showToast("网络错误")
    }
}
}
