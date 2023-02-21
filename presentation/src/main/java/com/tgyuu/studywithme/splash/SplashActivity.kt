package com.tgyuu.studywithme.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.tgyuu.studywithme.main.LoginActivity
import com.tgyuu.studywithme.R
import com.tgyuu.studywithme.introduce.IntroduceActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    lateinit var pref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        pref.edit().apply {
            putBoolean("pref", true)
            apply()
        }

        lifecycleScope.launch{
            delay(1500)
            firstCheck()
        }

    }

    private fun firstCheck(){
        if(pref.getBoolean("pref",true)){
            pref.edit().apply{
                putBoolean("pref",false)
                apply()
            }
            val intent = Intent(this, IntroduceActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}