package com.mashup.zuzu.bridge

import android.os.Handler
import android.util.Log
import android.webkit.JavascriptInterface
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class JavaScriptInterface(
    private val webView: ZuzuWebView,
    private val preference: WebPreference
) : JavaScriptInterfaceCallback {

    private val TAG = javaClass.simpleName
    private val handler = Handler()

    override fun onJavaScriptResponse(eventData: JsonObject) {
        val extra = eventData.toString()
        val loadUrlStr = "javascript:proof.event(\'$extra\')"
        Log.d(TAG, "loadUrlMsg: $loadUrlStr")
        try {
            webView.loadUrl(loadUrlStr)
        } catch (e: Exception) {
            Log.e(TAG, "Uncaught Reference Error: " + e.message)
        }
    }

    /** Web 에서 함수 호출시 호출됨 **/
    @JavascriptInterface
    fun call(funcName: String, _options: String, transactionId: String) {
        val options = JsonParser.parseString(_options).asJsonObject
        Log.d(TAG, "[Web Call] API full name: $funcName / options: $_options / transactionId: $transactionId")
        handler.post {
            WebAPIController.requestAPI(funcName, options, transactionId, preference)
        }
    }

    override fun onDestroy() {}
}