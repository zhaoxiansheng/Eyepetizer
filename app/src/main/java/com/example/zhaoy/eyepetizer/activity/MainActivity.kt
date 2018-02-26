package com.example.zhaoy.eyepetizer.activity

import android.os.Bundle
import com.example.zhaoy.eyepetizer.R
import com.example.zhaoy.eyepetizer.base.BaseActivity
import com.example.zhaoy.eyepetizer.base.currentFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRadio()
    }

    private fun setRadio() {
        rb_home.isChecked = true
        chooseFragment(R.id.rb_home)
        rg_root.setOnCheckedChangeListener { _, checkedId -> chooseFragment(checkedId) }
    }

    private fun chooseFragment(checkedId : Int){
        currentFragment = checkedId
    }
}
