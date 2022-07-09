package com.mashup.zuzu.ui.worldcup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mashup.zuzu.BuildConfig
import com.mashup.zuzu.R
import com.mashup.zuzu.bridge.WebPreference
import com.mashup.zuzu.databinding.ActivityWorldcupBinding

class WorldcupActivity : AppCompatActivity() {

    lateinit var binding: ActivityWorldcupBinding

    lateinit var webPreference: WebPreference

    private val viewModel by viewModels<WorldcupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_worldcup)
        binding.lifecycleOwner = this

        webPreference = WebPreferenceImpl(this, BuildConfig.APPLICATION_ID)

        initViews()
    }

    private fun initViews() {
        binding.viewModel = viewModel
        with(binding) {
            worldcupWebView.setJavaScriptInterface(webPreference)
            worldcupWebView.loadUrl("http://192.168.0.10:3000")
        }
    }
}