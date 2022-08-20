package com.mashup.zuzu.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kakao.sdk.user.UserApiClient
import com.mashup.zuzu.MainActivity
import com.mashup.zuzu.R
import com.mashup.zuzu.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        window.statusBarColor = getColor(R.color.black)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.actionFlow.collect { action ->
                    when (action) {
                        LoginViewModel.Action.ClickKakaoLogin -> getKakaoAccessToken()
                        LoginViewModel.Action.ClickSkip -> startMainActivity()
                        LoginViewModel.Action.ProofAuthSuccess -> startMainActivity()
                        LoginViewModel.Action.ProofAuthFailed ->
                            Toast.makeText(this@LoginActivity, "로그인에 실패했습니다.", Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            }
        }
    }

    private fun getKakaoAccessToken() {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                Timber.e("getKakaoAccessToken: Failed / error: ", error)
            } else if (token != null) {
                Timber.d("getKakaoAccessToken: Success / accessToken: ${token.accessToken}")
                viewModel.getProofAuthData(token.accessToken)
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0);
        finish()
    }
}