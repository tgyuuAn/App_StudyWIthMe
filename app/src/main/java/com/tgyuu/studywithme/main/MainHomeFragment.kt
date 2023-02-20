package com.tgyuu.studywithme.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tgyuu.studywithme.R
import com.tgyuu.studywithme.base.BaseFragment
import com.tgyuu.studywithme.databinding.FragmentMainHomeBinding

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(FragmentMainHomeBinding::inflate) {

    private var mBinding : FragmentMainHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMainHomeBinding.inflate(layoutInflater,container,false)

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}