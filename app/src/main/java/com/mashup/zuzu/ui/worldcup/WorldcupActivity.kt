package com.mashup.zuzu.ui.worldcup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mashup.zuzu.BuildConfig
import com.mashup.zuzu.ProofPreferenceImpl
import com.mashup.zuzu.R
import com.mashup.zuzu.bridge.ProofPreference
import com.mashup.zuzu.databinding.ActivityWorldcupBinding

class WorldcupActivity : AppCompatActivity() {

    lateinit var binding: ActivityWorldcupBinding

    lateinit var proofPreference: ProofPreference

    private val viewModel by viewModels<WorldcupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_worldcup)
        binding.lifecycleOwner = this

        proofPreference = ProofPreferenceImpl(this, BuildConfig.APPLICATION_ID)

        initViews()
    }

    private fun initViews() {
        binding.viewModel = viewModel
        with(binding) {
            worldcupWebView.setJavaScriptInterface(proofPreference)
            worldcupWebView.loadUrl("http://192.168.0.10:3000")
        }
    }
}