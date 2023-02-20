package com.tgyuu.studywithme.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.tgyuu.studywithme.R

typealias Inflate<B> = (LayoutInflater, ViewGroup?, Boolean) -> B

abstract class BaseFragment<B : ViewDataBinding>(private val inflate: Inflate<B>) : Fragment() {

    private var _binding: B? = null
    val binding: B
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}