package com.example.zhaoy.eyepetizer.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.zhaoy.eyepetizer.R
import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import com.example.zhaoy.eyepetizer.mvp.contract.CategoryContract
import com.example.zhaoy.eyepetizer.mvp.presenter.CategoryPresenter
import com.example.zhaoy.eyepetizer.toActivityWithParceable
import com.example.zhaoy.eyepetizer.ui.activity.WebViewActivity
import com.example.zhaoy.eyepetizer.ui.adapter.CategoryAdapter
import com.example.zhaoy.eyepetizer.ui.base.BaseFragment
import com.example.zhaoy.eyepetizer.ui.base.tabsId
import com.example.zhaoy.eyepetizer.utils.DisplayManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_category.*
import java.util.*

/**
 * Created by zhaoy on 2018/2/28.
 */
class CategoryFragment : BaseFragment(tabId = tabsId[1]), CategoryContract.IView {

    private val categoryPresenter: CategoryPresenter = CategoryPresenter(this)

    override fun showCategory(categories: ArrayList<ResponseClasses.Categories>) {
        adapter.setData(categories)
    }

    var isFirst = true

    var sIsScrolling = false

    private val adapter by lazy {
        CategoryAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutManager = GridLayoutManager(activity, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (gridLayoutManager.itemCount - 1 == position) {
                    return 2
                }
                return 1
            }

        }
        rv_category.layoutManager = gridLayoutManager
        rv_category.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        rv_category.adapter = adapter

        rv_category.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                val position = parent.getChildPosition(view)
                val offset = DisplayManager.dip2px(2f)!!

                outRect.set(if (position % 2 == 0) 0 else offset, offset,
                        if (position % 2 == 0) offset else 0, offset)
            }

        })
        //todo 由于recycleView 加载多图卡顿 监听滑动事件 当滑动停止时 加载图片 依旧卡顿
        rv_category.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    sIsScrolling = true;
                    Glide.with(activity).pauseRequests()
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (sIsScrolling) {
                        Glide.with(activity).resumeRequests()
                    }
                    sIsScrolling = false
                }
            }
        })

        adapter.onClick = { categories ->
            activity?.toActivityWithParceable<WebViewActivity>(categories)
        }
        categoryPresenter.requestData()
    }

    override fun onResume() {
        super.onResume()
        if (isFirst) {
            setupToolbar()
            isFirst = false
        }
    }

    override fun setupToolbar(): Boolean {
        if (super.setupToolbar()) {
            return true
        }
        super.setupToolbar()
        activity?.toolbar?.setBackgroundColor(0xddffffff.toInt())
        activity?.iv_search?.setImageResource(R.mipmap.ic_action_search)
        activity?.tv_bar_title?.setText("分类")
        return true
    }
}