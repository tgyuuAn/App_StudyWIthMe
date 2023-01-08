package com.tgyuu.studywithme

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.common.KakaoSdk

class MyApplication : MultiDexApplication() {
    companion object {
        lateinit var auth : FirebaseAuth
        var email: String? = null
    }

    override fun onCreate(){
        super.onCreate()
        auth = Firebase.auth
        KakaoSdk.init(this,"9802114bcf02cd95165f8fca4e3db86b")

    }
}