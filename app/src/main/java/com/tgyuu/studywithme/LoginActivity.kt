package com.tgyuu.studywithme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.tgyuu.studywithme.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)

        try{

            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            MyApplication.auth.signInWithCredential(credential)
                .addOnCompleteListener(this){

                    if (it.isSuccessful){

                        Log.d("구글로그인","구글로그인에 성공하였습니다.")
                        MyApplication.email = account.email
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        Log.d("구글로그인","구글로그인에 실패하였습니다.")
                    }
                }
        } catch (e : Exception){
            Log.d("구글로그인","구글로그인에 실패하였습니다. (예외)")
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoogle.setOnClickListener {
            loginGoogle()
        }

        binding.btnKakao.setOnClickListener {
            loginKaKao()
        }

    }
    private fun loginKaKao() {

        val Callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("카카오", "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i("카카오", "카카오계정으로 로그인 성공 ${token.accessToken}")

                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e("카카오", "사용자 정보 요청 실패", error)
                    }
                    else if (user != null) {
                        var scopes = mutableListOf<String>()

                        if (user.kakaoAccount?.emailNeedsAgreement == true) { scopes.add("account_email") }
                        if (user.kakaoAccount?.profileNeedsAgreement == true) { scopes.add("profile") }

                        if (scopes.count() > 0) {
                            Log.d("카카오", "사용자에게 추가 동의를 받아야 합니다.")


                            UserApiClient.instance.loginWithNewScopes(this, scopes) { token, error ->
                                if (error != null) {
                                    Log.e("카카오", "사용자 추가 동의 실패", error)
                                } else {
                                    Log.d("카카오", "allowed scopes: ${token!!.scopes}")

                                    // 사용자 정보 재요청
                                    UserApiClient.instance.me { user, error ->
                                        if (error != null) {
                                            Log.e("카카오", "사용자 정보 요청 실패", error)
                                        }
                                        else if (user != null) {
                                            Log.i("카카오", "사용자 정보 요청 성공")
                                            Log.d("카카오","\n회원번호: ${user.id}" +
                                                    "\n이메일: ${user.kakaoAccount?.email}" +
                                                    "\n닉네임: ${user.kakaoAccount?.profile?.nickname}")

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            // 카카오톡 로그인
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                // 로그인 실패 부분
                if (error != null) {
                    Log.e("카카오", "로그인 실패 $error")
                    // 사용자가 취소
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled ) {
                        return@loginWithKakaoTalk
                    }
                    // 다른 오류
                    else {
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = Callback) // 카카오 이메일 로그인
                    }
                }
                // 로그인 성공 부분
                else if (token != null) {
                    Log.e("카카오", "로그인 성공 ${token.accessToken}")

                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.e("카카오", "사용자 정보 요청 실패", error)
                        }
                        else if (user != null) {
                            var scopes = mutableListOf<String>()
                            Log.d("카카오", "사용자요청")
                            if (user.kakaoAccount?.emailNeedsAgreement == true) {
                                Log.d("카카오", "이메일 요청 확인")
                                scopes.add("account_email") }
                            if (user.kakaoAccount?.profileNeedsAgreement == true) {
                                Log.d("카카오", "프로필요청확인")
                                scopes.add("profile") }

                            if (scopes.count() > 0) {
                                Log.d("카카오", "사용자에게 추가 동의를 받아야 합니다.")


                                UserApiClient.instance.loginWithNewScopes(this, scopes) { token, error ->
                                    if (error != null) {
                                        Log.e("카카오", "사용자 추가 동의 실패", error)
                                    } else {
                                        Log.d("카카오", "allowed scopes: ${token!!.scopes}")

                                        // 사용자 정보 재요청
                                        UserApiClient.instance.me { user, error ->
                                            if (error != null) {
                                                Log.e("카카오", "사용자 정보 요청 실패", error)
                                            }
                                            else if (user != null) {
                                                Log.i("카카오", "사용자 정보 요청 성공")
                                                Log.d("카카오","\n회원번호: ${user.id}" +
                                                        "\n이메일: ${user.kakaoAccount?.email}" +
                                                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}")

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = Callback) // 카카오 이메일 로그인
        }
    }

    private fun loginGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val signInIntent = GoogleSignIn.getClient(this,gso).signInIntent
        requestLauncher.launch(signInIntent)
    }
}