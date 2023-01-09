package com.tgyuu.studywithme

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewpagerAdapter(activity : FragmentActivity) : FragmentStateAdapter(activity){

    val fragments : List<Fragment>

    init{
        fragments = listOf(IntroduceOneFragment(), IntroduceTwoFragment(), IntroduceThreeFragment())
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}