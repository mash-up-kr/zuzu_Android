package com.mashup.zuzu.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
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
import com.mashup.zuzu.ui.login.LoginViewModel.Action.*
import com.mashup.zuzu.ui.worldcup.WorldcupActivity
import com.mashup.zuzu.util.Constants
import com.mashup.zuzu.util.Key
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    private var canUseNetwork = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

        if (intent.hasExtra(Key.REQUEST_LOGIN_FROM_OTHER_CASES)) {
            viewModel.requestLoginFromOtherCases =
                intent.getBooleanExtra(Key.REQUEST_LOGIN_FROM_OTHER_CASES, false)
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.canUseNetwork.collect {
                    if (it) {
                        binding.networkTextView.visibility = View.GONE
                        canUseNetwork = true
                    } else {
                        binding.networkTextView.visibility = View.VISIBLE
                        canUseNetwork = false
                    }
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.actionFlow.collect { action ->
                    if (canUseNetwork) {
                        when (action) {
                            ClickKakaoLogin -> getKakaoAccessToken()
                            ProofAuthFailed ->
                                Toast.makeText(
                                    this@LoginActivity,
                                    "로그인에 실패했습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            StartMain -> startProof(Constants.MAIN_ACTIVITY)
                            StartWorldcup -> startProof(Constants.WORLDCUP_ACTIVITY)
                            CloseLoginBySuccess -> closeLogin(true)
                            SkipLogin -> closeLogin(false)
                        }
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "네트워크 상태 확인 후 다시 시도해 주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        window.statusBarColor = getColor(R.color.black)
    }

    private fun startProof(targetActivity: String?) {
        val intent: Intent
        when (targetActivity) {
            Constants.MAIN_ACTIVITY -> {
                intent = Intent(this@LoginActivity, MainActivity::class.java)
            }
            else -> {
                intent = Intent(this@LoginActivity, WorldcupActivity::class.java)
                intent.putExtra(Key.NEXT_ACTIVITY, Constants.MAIN_ACTIVITY)
            }
        }
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }

    private fun closeLogin(isSuccess: Boolean) {
        if (isSuccess) {
            setResult(Activity.RESULT_OK)
        }
        finish()
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
}