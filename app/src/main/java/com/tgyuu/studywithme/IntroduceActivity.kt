package com.tgyuu.studywithme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tgyuu.studywithme.databinding.ActivityIntroduceBinding

class IntroduceActivity : AppCompatActivity() {
    lateinit var binding : ActivityIntroduceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroduceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewpager2
        viewPager.adapter = ViewpagerAdapter(this)

        binding.dotsIndicator.attachTo(viewPager)
    }
}