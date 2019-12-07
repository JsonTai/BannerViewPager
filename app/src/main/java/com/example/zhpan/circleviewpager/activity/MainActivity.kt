package com.example.zhpan.circleviewpager.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

import com.example.zhpan.circleviewpager.R
import com.example.zhpan.circleviewpager.adapter.AdapterFragmentPager

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        setListener()
    }

    private fun initData() {
        vp_fragment.adapter = AdapterFragmentPager(this)
        vp_fragment.offscreenPageLimit = 3
        vp_fragment.isUserInputEnabled=false
        vp_fragment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                rg_tab?.check(getCheckedId(position))
            }
        })
    }

    private fun getCheckedId(position: Int): Int {
        var checkedId = R.id.rb_home
        when (position) {
            0 -> checkedId = R.id.rb_home
            1 -> checkedId = R.id.rb_find
            2 -> checkedId = R.id.rb_add
            3 -> checkedId = R.id.rb_others
        }
        return checkedId
    }

    private fun setListener() {
        rg_tab?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_home -> vp_fragment.setCurrentItem(AdapterFragmentPager.PAGE_HOME, false)
                R.id.rb_find -> vp_fragment.setCurrentItem(AdapterFragmentPager.PAGE_FIND, false)
                R.id.rb_add -> vp_fragment.setCurrentItem(AdapterFragmentPager.PAGE_INDICATOR, false)
                R.id.rb_others -> vp_fragment.setCurrentItem(AdapterFragmentPager.PAGE_OTHERS, false)
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
