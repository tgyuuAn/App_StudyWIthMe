package com.tgyuu.studywithme.introduce

import android.os.Bundle
import com.tgyuu.studywithme.base.BaseActivity
import com.tgyuu.studywithme.databinding.ActivityIntroduceBinding

class IntroduceActivity : BaseActivity<ActivityIntroduceBinding>({ActivityIntroduceBinding.inflate(it)}){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewPager = binding.viewpager2
        viewPager.adapter = ViewpagerAdapter(this)

        binding.dotsIndicator.attachTo(viewPager)
    }
}