package com.example.zhaoy.eyepetizer.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zhaoy.eyepetizer.R
import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import com.example.zhaoy.eyepetizer.mvp.contract.CategoryContract
import com.example.zhaoy.eyepetizer.mvp.presenter.CategoryPresenter
import com.example.zhaoy.eyepetizer.ui.adapter.CategoryAdapter
import com.example.zhaoy.eyepetizer.ui.base.BaseFragment
import com.example.zhaoy.eyepetizer.ui.base.tabsId
import com.example.zhaoy.eyepetizer.utils.DisplayManager
import kotlinx.android.synthetic.main.fragment_category.*
import java.util.ArrayList

class CategoryFragment : BaseFragment(tabId = tabsId[1]), CategoryContract.IView{

    val categoryPresenter: CategoryPresenter

    init {
        categoryPresenter = CategoryPresenter(this)
    }

    override fun showCategory(categories: ArrayList<ResponseClasses.Categories>) {
        adapter.setData(categories)
    }

    var isFirst = true

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
        categoryPresenter.requestData()
    }

    override fun onResume() {
        super.onResume()
        if (isFirst) {
            setupToolbar()
            isFirst = false
        }
    }
}