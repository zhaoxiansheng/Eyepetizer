package com.example.zhaoy.eyepetizer

import android.app.Activity
import android.app.ActivityOptions
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import java.io.Serializable
import java.util.*

const val TAG = "ToolUtils"

//todo： by zy 2018/3/7 16：36 修改成单例
fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(this, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun Fragment.showToast(content: String): Toast {
    val toast = Toast.makeText(this.activity, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun View.showToast(content: String): Toast {
    val toast = Toast.makeText(this.context, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

// TODO: by zy 2018/3/7 16:23 关于inline reified
inline fun <reified T : Activity> Context.toActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T : Activity> Context.toActivityWithParceable(data: Parcelable){
    val intent = Intent(this, T::class.java)
    intent.putExtra("data", data)
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.toAnimActivity(){
    val intent = Intent(this, T::class.java)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    } else {
        startActivity(intent)
    }
}

inline fun <reified T : Activity> Activity.toAnimActivityWithParceable(data: Parcelable) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("data", data)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    } else {
        startActivity(intent)
    }
}

fun View.durationFormat(duration: Long?): String {
    val minute = duration!! / 60
    val second = duration % 60
    if (minute <= 9) {
        if (second <= 9) {
            return "0${minute}' 0${second}''"
        } else {
            return "0${minute}' ${second}''"
        }
    } else {
        if (second <= 9) {
            return "${minute}' 0${second}''"
        } else {
            return "${minute}' ${second}''"
        }
    }
}

fun View.timeFormat(time: Long): String {
    val date = Date(time)
    val timeCalendar = Calendar.getInstance()
    timeCalendar.time = date


    val today = Calendar.getInstance()
    val todayDate = Date(System.currentTimeMillis())
    today.time = todayDate

    if (timeCalendar.get(Calendar.YEAR) === today.get(Calendar.YEAR)) {
        val diffDay = timeCalendar.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR)

        if (diffDay == 0) {
            //是今天
            val hours = timeCalendar.get(Calendar.HOUR_OF_DAY)
            val minues = timeCalendar.get(Calendar.MINUTE)
            return "${if (hours < 10) "0" + hours else hours}:${if (minues < 10) "0" + minues else minues}"
        }
    }
    val year = timeCalendar.get(Calendar.YEAR)
    val month = timeCalendar.get(Calendar.MONTH)
    val day = timeCalendar.get(Calendar.DAY_OF_MONTH)
    return "${year}/${if (month < 10) "0" + month else month}/${if (day < 10) "0" + day else day}"
}

/**
 * 几天前  几小时前
 */
fun View.timePreFormat(time: Long): String {

    val now =System.currentTimeMillis()
    val pre = now - time//多久前


    val min = pre / 1000 / 60
    if (min<1){
        return "刚刚"
    }else if(min<60){
        return ""+min+"分钟前"
    }else if(min<60*24){
        return ""+min/60+"小时前"
    }else {
        return ""+min/60/24+"天前"
    }
}

inline fun <reified T : Activity> Context.toActivityWithSerializable(data: Serializable) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("data", data)
    startActivity(intent)
}