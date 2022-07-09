package com.mashup.zuzu.bridge

import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient

class ZuzuWebChromeClient : WebChromeClient() {
    private val TAG = javaClass.simpleName

    /** JavaScript Console Message 가 전달됨 **/
    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        Log.i(
            TAG,
            "[JavaScript Msg] " + consoleMessage.message() + " -- from Line #" + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId()
        )
        return true
    }

    /** 클라이언트에 권한이 필요할 때 호출됨 **/
    override fun onPermissionRequest(request: PermissionRequest) {
        request.grant(request.resources)
    }
}