package com.example.zhaoy.eyepetizer.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager
import android.view.WindowManager
import com.example.zhaoy.eyepetizer.R
import java.util.*

/**
 * Created on 2018/2/25.
 *
 * @author zhaoy
 */

class NetworkUtils private constructor() {

    init {
        throw UnsupportedOperationException("u can't instance me...")
    }

    companion object {

        private var mWifiDialog: AlertDialog? = null

        fun showWifiDlg(context: Context) {
            val builder = AlertDialog.Builder(context.applicationContext)
            if (mWifiDialog == null) {
                mWifiDialog = builder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("请检查网络")
                        .setMessage("当前无网络连接").setPositiveButton("退出") { dialog, which -> System.exit(0) }.setNegativeButton("知道了", null).create()
                // 设置为系统的Dialog，这样使用Application的时候不会 报错
                mWifiDialog!!.window!!.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
                mWifiDialog!!.setCancelable(false)
            }
            mWifiDialog!!.show()
        }

        // wifi network
        val NETWORK_WIFI = 1
        // "4G" networks
        val NETWORK_4G = 4
        // "3G" networks
        val NETWORK_3G = 3
        // "2G" networks
        val NETWORK_2G = 2
        // unknown network
        val NETWORK_UNKNOWN = 5
        // no network
        val NETWORK_NO = -1

        private val NETWORK_TYPE_GSM = 16
        private val NETWORK_TYPE_TD_SCDMA = 17
        private val NETWORK_TYPE_IWLAN = 18

        var typeList: MutableList<String> = ArrayList()

        init {
            typeList.add("打开网络设置界面")
            typeList.add("获取活动网络信息")
            typeList.add("判断网络是否可用")
            typeList.add("判断网络是否是4G")
            typeList.add("判断wifi是否连接状态")
            typeList.add("获取移动网络运营商名称")
            typeList.add("获取当前的网络类型")
            typeList.add("获取当前的网络类型(WIFI,2G,3G,4G)")


        }

        /**
         * 打开网络设置界面
         * 3.0以下打开设置界面
         *
         * @param context 上下文
         */
        fun openWirelessSettings(context: Context) {
            if (android.os.Build.VERSION.SDK_INT > 10) {
                context.startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
            } else {
                context.startActivity(Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS))
            }
        }

        /**
         * 获取活动网络信息
         *
         * @param context 上下文
         * @return NetworkInfo
         */
        fun getActiveNetworkInfo(context: Context): NetworkInfo? {
            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }

        /**
         * 判断网络是否可用
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @param context 上下文
         * @return `true`: 可用<br></br>`false`: 不可用
         */
        fun isAvailable(context: Context): Boolean {
            val info = getActiveNetworkInfo(context)
            return info != null && info.isAvailable
        }

        /**
         * 判断网络是否连接
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @param context 上下文
         * @return `true`: 是`false`: 否
         */
        fun isConnected(context: Context): Boolean {
            val info = getActiveNetworkInfo(context)
            return info != null && info.isConnected
        }

        /**
         * 判断网络是否是4G
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @param context 上下文
         * @return `true`: 是<br></br>`false`: 不是
         */
        fun is4G(context: Context): Boolean {
            val info = getActiveNetworkInfo(context)
            return info != null && info.isAvailable && info.subtype == TelephonyManager.NETWORK_TYPE_LTE
        }

        /**
         * 判断wifi是否连接状态
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @param context 上下文
         * @return `true`: 连接<br></br>`false`: 未连接
         */
        fun isWifiConnected(context: Context): Boolean {
            val cm = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm != null && cm.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
        }

        /**
         * 获取移动网络运营商名称
         * 如中国联通、中国移动、中国电信
         *
         * @param context 上下文
         * @return 移动网络运营商名称
         */
        fun getNetworkOperatorName(context: Context): String? {
            val tm = context
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return tm?.networkOperatorName
        }

        /**
         * 获取移动终端类型
         *
         * @param context 上下文
         * @return 手机制式
         * [TelephonyManager.PHONE_TYPE_NONE] : 0 手机制式未知
         * [TelephonyManager.PHONE_TYPE_GSM] : 1 手机制式为GSM，移动和联通
         * [TelephonyManager.PHONE_TYPE_CDMA] : 2 手机制式为CDMA，电信
         * [TelephonyManager.PHONE_TYPE_SIP] : 3
         */
        fun getPhoneType(context: Context): Int {
            val tm = context
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return tm?.phoneType ?: -1
        }


        /**
         * 获取当前的网络类型(WIFI,2G,3G,4G)
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @param context 上下文
         * @return 网络类型
         * [.NETWORK_WIFI] = 1;
         * [.NETWORK_4G] = 4;
         * [.NETWORK_3G] = 3;
         * [.NETWORK_2G] = 2;
         * [.NETWORK_UNKNOWN] = 5;
         * [.NETWORK_NO] = -1;
         */
        fun getNetWorkType(context: Context): Int {
            var netType = NETWORK_NO
            val info = getActiveNetworkInfo(context)
            if (info != null && info.isAvailable) {

                if (info.type == ConnectivityManager.TYPE_WIFI) {
                    netType = NETWORK_WIFI
                } else if (info.type == ConnectivityManager.TYPE_MOBILE) {
                    when (info.subtype) {

                        NETWORK_TYPE_GSM, TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> netType = NETWORK_2G

                        NETWORK_TYPE_TD_SCDMA, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> netType = NETWORK_3G

                        NETWORK_TYPE_IWLAN, TelephonyManager.NETWORK_TYPE_LTE -> netType = NETWORK_4G
                        else -> {

                            val subtypeName = info.subtypeName
                            if (subtypeName.equals("TD-SCDMA", ignoreCase = true)
                                    || subtypeName.equals("WCDMA", ignoreCase = true)
                                    || subtypeName.equals("CDMA2000", ignoreCase = true)) {
                                netType = NETWORK_3G
                            } else {
                                netType = NETWORK_UNKNOWN
                            }
                        }
                    }
                } else {
                    netType = NETWORK_UNKNOWN
                }
            }
            return netType
        }

        /**
         * 获取当前的网络类型(WIFI,2G,3G,4G)
         * 依赖上面的方法
         *
         * @param context 上下文
         * @return 网络类型名称
         *
         * NETWORK_WIFI
         * NETWORK_4G
         * NETWORK_3G
         * NETWORK_2G
         * NETWORK_UNKNOWN
         * NETWORK_NO
         */
        fun getNetWorkTypeName(context: Context): String {
            when (getNetWorkType(context)) {
                NETWORK_WIFI -> return "NETWORK_WIFI"
                NETWORK_4G -> return "NETWORK_4G"
                NETWORK_3G -> return "NETWORK_3G"
                NETWORK_2G -> return "NETWORK_2G"
                NETWORK_NO -> return "NETWORK_NO"
                else -> return "NETWORK_UNKNOWN"
            }
        }
    }
}