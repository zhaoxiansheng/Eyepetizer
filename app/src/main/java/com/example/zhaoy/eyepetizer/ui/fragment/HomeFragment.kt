package com.example.zhaoy.eyepetizer.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zhaoy.eyepetizer.R
import com.example.zhaoy.eyepetizer.TAG
import com.example.zhaoy.eyepetizer.bean.HomeBean
import com.example.zhaoy.eyepetizer.bean.Item
import com.example.zhaoy.eyepetizer.mvp.contract.HomeContract
import com.example.zhaoy.eyepetizer.mvp.presenter.HomePresenter
import com.example.zhaoy.eyepetizer.ui.adapter.HomeAdapter
import com.example.zhaoy.eyepetizer.ui.base.BaseFragment
import com.example.zhaoy.eyepetizer.ui.base.tabsId
import com.example.zhaoy.eyepetizer.ui.view.home.PullRecyclerView
import com.example.zhaoy.eyepetizer.ui.view.home.banner.HomeBanner
import com.example.zhaoy.eyepetizer.ui.view.home.banner.HomeBannerItem
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : BaseFragment(tabId = tabsId[0]), HomeContract.IView {

    private val simpleDateFormat by lazy { SimpleDateFormat("- MMM. dd, 'Brunch' -", Locale.ENGLISH) }

    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }

    var presenter: HomePresenter = HomePresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        presenter.requestFirstData()
    }

    var loadingMore = false

    private fun initView() {

        activity?.tv_bar_title?.typeface = Typeface.createFromAsset(activity?.assets, "fonts/Lobster-1.4.otf")
        val paint = activity?.tv_bar_title?.paint
        paint?.isFakeBoldText = true

        rv_home.adapter = homeAdapter
        rv_home.layoutManager = LinearLayoutManager(activity)
        rv_home.setOnRefreshListener(object : PullRecyclerView.OnRefreshListener {
            override fun onRefresh() {
                presenter.requestFirstData()
            }
        })

        rv_home.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val childCount = rv_home.childCount
                    val itemCount = rv_home.layoutManager.itemCount
                    val firstVisibleItem = (rv_home.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {
                        Log.d(TAG, "到底了")
                        if (!loadingMore) {
                            loadingMore = true
                            onLoadMore()
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                setupToolbar()
            }
        })

    }


    private val linearLayoutManager by lazy {
        rv_home.layoutManager as LinearLayoutManager
    }

    /**
     * recyclerview滚动的时候会调用这里，在这里设置toolbar
     */
    override fun setupToolbar(): Boolean {
        if (super.setupToolbar()) {
            return true
        }
        super.setupToolbar()
        val findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
        if (findFirstVisibleItemPosition == 0) {//设置为透明
            activity?.toolbar?.setBackgroundColor(0x00000000)
            activity?.iv_search?.setImageResource(R.mipmap.ic_action_search_white)
            activity?.tv_bar_title?.text = ""
        } else {
            if (homeAdapter.itemList.size > 1) {

                activity?.toolbar?.setBackgroundColor(0xddffffff.toInt())
                activity?.iv_search?.setImageResource(R.mipmap.ic_action_search)
                val itemList = homeAdapter.itemList
                val item = itemList[findFirstVisibleItemPosition + homeAdapter.bannerItemListCount - 1]
                if (item.type == "textHeader") {
                    activity?.tv_bar_title?.text = item.data?.text
                } else {
                    activity?.tv_bar_title?.text = simpleDateFormat.format(item.data?.date)
                }
            }

        }
        return true
    }

    fun onLoadMore() {
        presenter.requestMoreData()
    }


    override fun setMoreData(itemList: ArrayList<Item>) {
        loadingMore = false
        homeAdapter.addData(itemList)
    }

    override fun setFirstData(homeBean: HomeBean) {
        homeAdapter.setBannerSize(homeBean.issueList[0].count)
        homeAdapter.itemList = homeBean.issueList[0].itemList
        rv_home.hideLoading()
    }

    override fun onError() {
        rv_home.hideLoading()
    }

    override fun onResume() {
        super.onResume()
        if (rv_home.getChildAt(0) is HomeBanner) {
            for (index in 0 until (rv_home.getChildAt(0) as HomeBanner).viewPager.childCount) {
                val homeBannerItem = (rv_home.getChildAt(0) as HomeBanner).viewPager.getChildAt(index) as HomeBannerItem
                if (homeBannerItem.isVideo) {
                    homeBannerItem.setUpView()//重新设置视频
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        GSYVideoPlayer.releaseAllVideos()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}