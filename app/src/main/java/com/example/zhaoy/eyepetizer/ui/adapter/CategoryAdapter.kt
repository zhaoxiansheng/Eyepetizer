package com.example.zhaoy.eyepetizer.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.zhaoy.eyepetizer.bean.ResponseClasses
import com.example.zhaoy.eyepetizer.ui.view.category.CategoryItem

/**
 * Created by zhaoy on 2018/2/28.
 */
class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    val data by lazy { ArrayList<ResponseClasses.Categories>() }

    fun setData(data: ArrayList<ResponseClasses.Categories>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    //todo 应该由多个类型 最后的 the-end 类型未处理
    private val TYPE_STANDARD = 1

    override fun getItemViewType(position: Int): Int {
        return TYPE_STANDARD
    }


    override fun getItemCount(): Int {
        if (data.size > 0) {
            return data.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val itemViewType = getItemViewType(position)
        holder?.itemView?.tag = data[position].bgPicture
        when (itemViewType) {
            TYPE_STANDARD -> {
                if (data[position].bgPicture == holder?.itemView?.tag){
                    (holder.itemView as CategoryItem).setData(data[position])
                    holder.itemView.setOnClickListener { onClick?.invoke(data[position]) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var itemView: View? = null
        when (viewType) {
            TYPE_STANDARD -> {
                itemView = CategoryItem(parent?.context)
            }
        }
        return CategoryAdapter.ViewHolder(itemView)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    var onClick: ((ResponseClasses.Categories) -> Unit)? = {}
}