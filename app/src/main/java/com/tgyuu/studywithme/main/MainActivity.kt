package com.tgyuu.studywithme.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.material.tabs.TabLayout
import com.tgyuu.studywithme.R
import com.tgyuu.studywithme.base.BaseActivity
import com.tgyuu.studywithme.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }
}