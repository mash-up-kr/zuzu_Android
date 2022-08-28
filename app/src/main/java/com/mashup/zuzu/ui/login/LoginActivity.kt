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
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
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
                            ClickKakaoLogin -> Kakao().getKakaoAccessToken()
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

    private inner class Kakao {
        fun getKakaoAccessToken() {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
                loginWithKakaoTalk()
            } else {
                loginWithKakaoAccount()
            }
        }

        private fun loginWithKakaoTalk() {
            UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity) { token, error ->
                if (error != null) {
                    Timber.e("getKakaoAccessToken: Failed / error: ", error)
                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    loginWithKakaoAccount()
                } else if (token != null) {
                    Timber.d("getKakaoAccessToken: Success / accessToken: ${token.accessToken}")
                    viewModel.getProofAuthData(token.accessToken)
                }
            }
        }

        private fun loginWithKakaoAccount() {
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Timber.e("getKakaoAccessToken: Failed / error: ", error)
                } else if (token != null) {
                    Timber.d("getKakaoAccessToken: Success / accessToken: ${token.accessToken}")
                    viewModel.getProofAuthData(token.accessToken)
                }
            }
            UserApiClient.instance.loginWithKakaoAccount(
                this@LoginActivity,
                callback = callback
            )
        }
    }
}