package com.mashup.zuzu.ui.worldcup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.zuzu.MainActivity
import com.mashup.zuzu.R
import com.mashup.zuzu.bridge.ProofPreference
import com.mashup.zuzu.bridge.WebAPIController
import com.mashup.zuzu.databinding.ActivityWorldcupBinding
import com.mashup.zuzu.util.Constants
import com.mashup.zuzu.util.Key
import com.mashup.zuzu.util.WebConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WorldcupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorldcupBinding

    @Inject
    lateinit var proofPreference: ProofPreference

    private var nextActivity = ""

    private val viewModel by viewModels<WorldcupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.hasExtra(Key.NEXT_ACTIVITY)) {
            nextActivity = intent.getStringExtra(Key.NEXT_ACTIVITY).toString()
        }
        initViews()
        observeWebRequest()
    }

    private fun initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_worldcup)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        with(binding) {
            worldcupWebView.setJavaScriptInterface(proofPreference)
            worldcupWebView.loadUrl(WebConstants.URL.WORLDCUP)
        }
    }

    private fun observeWebRequest() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                WebAPIController.channelFlow.collect { jsonObject ->
                    when (jsonObject.get(WebConstants.FUNCTION_NAME).asString) {
                        WebAPIController.FunctionName.CLOSE_WEB_VIEW -> finish()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (binding.worldcupWebView.canGoBack()) {
            binding.worldcupWebView.goBack()
        } else {
            if (nextActivity == Constants.MAIN_ACTIVITY) {
                val intent = Intent(this@WorldcupActivity, MainActivity::class.java)
                startActivity(intent)
            }
            finish()
        }
    }
}