package com.example.zhaoy.eyepetizer.ui.activity

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.zhaoy.eyepetizer.R
import com.example.zhaoy.eyepetizer.bean.Item
import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import com.example.zhaoy.eyepetizer.mvp.model.CategoryDetailContract
import com.example.zhaoy.eyepetizer.mvp.presenter.CategoryDetailPresenter
import com.example.zhaoy.eyepetizer.showToast
import com.example.zhaoy.eyepetizer.ui.adapter.CategoryDetailAdapter
import com.example.zhaoy.eyepetizer.ui.base.BaseActivity
import com.example.zhaoy.eyepetizer.utils.DisplayManager
import kotlinx.android.synthetic.main.activity_category_detail.*
import java.util.logging.Logger

/**
 * Created by zhaoy on 2018/3/7.
 */
class CategoryDetailActivity : BaseActivity(), CategoryDetailContract.IView {

    companion object {
        private const val TAG = "CategoryDetailActivity"
    }

    private val adapter by lazy { CategoryDetailAdapter() }

    private lateinit var categoryDetailPresenter: CategoryDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
        val category = intent.getSerializableExtra("data") as ResponseClasses.Categories

        categoryDetailPresenter = CategoryDetailPresenter(this)
        initView()
        categoryDetailPresenter.start(category)
    }

    private fun initView() {
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
                    val childCount = rv_category_detail.childCount
                    val itemCount = rv_category_detail.layoutManager.itemCount
                    val firstVisibleItem = (rv_category_detail.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {
                        Log.d(Companion.TAG, "到底了")
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

    override fun setHeader(category: ResponseClasses.Categories) {
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
