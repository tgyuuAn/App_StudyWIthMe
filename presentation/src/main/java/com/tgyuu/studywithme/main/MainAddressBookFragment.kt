package com.tgyuu.studywithme.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tgyuu.studywithme.R
import com.tgyuu.studywithme.base.BaseFragment
import com.tgyuu.studywithme.databinding.FragmentMainAddressBookBinding

class MainAddressBookFragment : BaseFragment<FragmentMainAddressBookBinding>(FragmentMainAddressBookBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}