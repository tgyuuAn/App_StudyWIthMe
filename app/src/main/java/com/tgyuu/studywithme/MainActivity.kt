package com.tgyuu.studywithme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.tgyuu.studywithme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentFirstSet()
        binding.mainTablayout.addOnTabSelectedListener(tabListner())

    }
    private fun fragmentFirstSet(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_frameLayout,MainHomeFragment())
        transaction.commit()
        Log.d("확인","프래그먼트 홈화면 첫 생성")
    }


    private fun settingsSelected(transaction: FragmentTransaction) {
        transaction.replace(R.id.main_frameLayout, MainSettingsFragment())
        Log.d("확인", "설정버튼 선택됌")
    }

    private inner class tabListner : TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab?) {
            val transaction = supportFragmentManager.beginTransaction()
            Log.d("확인","On Tab Selected 탭 버튼 선택됌")
            when (tab?.position) {
                0 -> homeSelected(transaction)
                1 -> studySelected(transaction)
                2 -> addressSelected(transaction)
                3 -> settingsSelected(transaction)
                else -> Unit
            }
            transaction.addToBackStack(null)
            transaction.commit()
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            Log.d("확인","on Tab Unselected 탭 버튼 선택됌")
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            Log.d("확인","On Tab Reselected 탭 버튼 선택됌")
        }

        private fun homeSelected(transaction : FragmentTransaction){
            transaction.replace(R.id.main_frameLayout, MainHomeFragment())
            Log.d("확인","홈버튼 선택됌")
        }

        private fun studySelected(transaction: FragmentTransaction) {
            transaction.replace(R.id.main_frameLayout, MainStudyFragment())
            Log.d("확인", "스터디버튼 선택됌")
        }

        private fun addressSelected(transaction: FragmentTransaction) {
            transaction.replace(R.id.main_frameLayout, MainAddressBookFragment())
            Log.d("확인", "주소록버튼 선택됌")
        }

    }
}
