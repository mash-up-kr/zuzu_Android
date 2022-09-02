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
import com.mashup.zuzu.bridge.WebAPIController.FunctionName
import com.mashup.zuzu.databinding.ActivityWorldcupBinding
import com.mashup.zuzu.util.Constants
import com.mashup.zuzu.util.Key
import com.mashup.zuzu.util.WebConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
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
        var subUrl = ""
        if (intent.hasExtra(Key.NEXT_ACTIVITY)) {
            nextActivity = intent.getStringExtra(Key.NEXT_ACTIVITY).toString()
        }
        if (intent.hasExtra(Key.RECOMMENDED_WORLDCUP_ID)) {
            val recommendedWorldcupId = intent.getStringExtra(Key.RECOMMENDED_WORLDCUP_ID).toString()
            subUrl = "/$recommendedWorldcupId"
        }
        if (intent.hasExtra(Key.MY_WINNING_DRINK_ID)) {
            val myWinningWorldcupId = intent.getStringExtra(Key.MY_WINNING_DRINK_ID).toString()
            subUrl = "/result/view/$myWinningWorldcupId"
        }
        initViews(subUrl)
        observeWebRequest()
    }

    private fun initViews(subUrl: String) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_worldcup)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        with(binding) {
            worldcupWebView.setJavaScriptInterface(proofPreference)
            worldcupWebView.loadUrl(WebConstants.URL.WORLDCUP + subUrl)
            Timber.d("loaded url: ${WebConstants.URL.WORLDCUP}$subUrl")
        }
    }

    private fun observeWebRequest() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                WebAPIController.channelFlow.collect { jsonObject ->
                    when (jsonObject.get(WebConstants.FUNCTION_NAME).asString) {
                        FunctionName.CLOSE_WEB_VIEW -> finish()
                        FunctionName.ON_CLICK_SHARED_BUTTON -> showSharedBottomSheet(
                            jsonObject.get(WebConstants.KEY_URL).asString
                        )
                    }
                }
            }
        }
    }

    private fun showSharedBottomSheet(url: String) {
        if (url.isNotEmpty()) {
            val shareIntent = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                putExtra(Intent.EXTRA_TEXT, url)
            }, null)
            startActivity(shareIntent)
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