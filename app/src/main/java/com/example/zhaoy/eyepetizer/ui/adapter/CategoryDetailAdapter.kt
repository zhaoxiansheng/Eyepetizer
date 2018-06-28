package com.example.zhaoy.eyepetizer.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.zhaoy.eyepetizer.bean.Item
import com.example.zhaoy.eyepetizer.toActivityWithSerializable
import com.example.zhaoy.eyepetizer.ui.activity.DetailActivity
import com.example.zhaoy.eyepetizer.ui.view.common.StandardVideoItem

class CategoryDetailAdapter : RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder>() {

    private val categorys: ArrayList<Item> by lazy {
        ArrayList<Item>()
    }

    fun addData(itemList: ArrayList<Item>) {
        this.categorys.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        (holder?.itemView as StandardVideoItem).let {
            it.setOnClickListener { v -> v.context.toActivityWithSerializable<DetailActivity>(categorys[position]) }
            it.setData(categorys[position], "categorydetail")
        }

    }

    override fun getItemCount(): Int = categorys.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(StandardVideoItem(parent!!.context))
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}