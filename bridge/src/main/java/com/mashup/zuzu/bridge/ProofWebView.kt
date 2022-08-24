package com.mashup.zuzu.bridge

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView

class ProofWebView : WebView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun destroy() {
        clearHistory()
        destroyDrawingCache()
        removeAllViews()
        clearCache(true)
        setWebContentsDebuggingEnabled(false)
        super.destroy()
    }

    fun setJavaScriptInterface(preference: ProofPreference) {
        val javaScriptInterface = JavaScriptInterface(this, preference)
        addJavascriptInterface(javaScriptInterface, "proof_native_api")
        WebAPIController.jsInterface = javaScriptInterface
        clearCache(true)
        requestFocus()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        webChromeClient = ProofWebChromeClient()
        webViewClient = ProofWebViewClient()
        setWebContentsDebuggingEnabled(true)
        applySettings()
        setBackgroundColor(Color.argb(1, 0, 0, 0))
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Suppress("deprecation")
    private fun applySettings() {
        settings.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            useWideViewPort = true
            domStorageEnabled = true
            loadWithOverviewMode = true
            mediaPlaybackRequiresUserGesture = false
            allowFileAccess = true
            allowContentAccess = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            userAgentString += "_Proof Android"

            // https://stackoverflow.com/questions/7422427/android-webview-slow
            setRenderPriority(WebSettings.RenderPriority.HIGH)
            setLayerType(View.LAYER_TYPE_HARDWARE, null)
            cacheMode = WebSettings.LOAD_NO_CACHE
        }
    }
}