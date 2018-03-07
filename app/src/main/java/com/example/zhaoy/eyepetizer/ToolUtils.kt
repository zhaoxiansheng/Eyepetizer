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