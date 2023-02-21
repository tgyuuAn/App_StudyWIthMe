package com.tgyuu.studywithme.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(val bindingFactory : (LayoutInflater) -> B) : AppCompatActivity() {

    protected lateinit var binding : B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        binding.setLifecycleOwner(this)
        setContentView(binding.root)
    }

    //뒤로가기 버튼 막기
    //override fun onBackPressed(){}

    fun makeToast(str : String) = Toast.makeText(this,str,Toast.LENGTH_SHORT).show()

}