package com.tgyuu.studywithme.introduce

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tgyuu.studywithme.introduce.IntroduceOneFragment
import com.tgyuu.studywithme.introduce.IntroduceThreeFragment
import com.tgyuu.studywithme.introduce.IntroduceTwoFragment

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