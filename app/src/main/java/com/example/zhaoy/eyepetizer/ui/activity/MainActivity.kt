package com.example.zhaoy.eyepetizer.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.zhaoy.eyepetizer.R
import com.example.zhaoy.eyepetizer.ui.base.BaseActivity
import com.example.zhaoy.eyepetizer.ui.base.BaseFragment
import com.example.zhaoy.eyepetizer.ui.base.currentFragment
import com.example.zhaoy.eyepetizer.ui.base.tabsId
import com.example.zhaoy.eyepetizer.ui.fragment.CategoryFragment
import com.example.zhaoy.eyepetizer.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRadio()
    }

    private fun setRadio() {
        rb_category.isChecked = true
        chooseFragment(R.id.rb_home)
        rg_root.setOnCheckedChangeListener { _, checkedId -> chooseFragment(checkedId) }
    }

    private fun chooseFragment(checkedId: Int) {
        currentFragment = checkedId

        val beginTransaction = supportFragmentManager.beginTransaction()

        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(checkedId.toString())
        if (fragment == null) {
            when (checkedId) {
                R.id.rb_home -> beginTransaction.add(R.id.fl_content, HomeFragment(), checkedId.toString())
                R.id.rb_category -> beginTransaction.add(R.id.fl_content, CategoryFragment(), checkedId.toString())
            }
        }
        tabsId.forEach { tab ->

            val aFragment = supportFragmentManager.findFragmentByTag(tab.toString()) as BaseFragment?

            if (tab == checkedId) {
                aFragment?.let {
                    it.setupToolbar()
                    beginTransaction.show(it)
                }
            } else {
                aFragment?.let {
                    beginTransaction.hide(it)
                }
            }
        }


        beginTransaction.commit()

    }
}
