package com.tgyuu.studywithme

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tgyuu.studywithme.databinding.FragmentThreeBinding

class ThreeFragment : Fragment() {
    lateinit var binding : FragmentThreeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentThreeBinding.inflate(layoutInflater)
        binding.btnStart.setOnClickListener {
            moveToLoginActivity()
        }
        return binding.root
    }

    private fun moveToLoginActivity(){
        val intent = Intent(activity,LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

}