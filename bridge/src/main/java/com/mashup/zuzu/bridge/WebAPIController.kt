package com.mashup.zuzu.bridge

import com.google.gson.JsonObject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

object WebAPIController {

    private val channel = Channel<JsonObject>()
    val channelFlow: Flow<JsonObject>
        get() = channel.receiveAsFlow()

    internal lateinit var jsInterface: JavaScriptInterface

    private const val CALLBACK_EVENT = "CALLBACK_EVENT"
    private const val BACK_BUTTON_EVENT = "BACK_BUTTON_EVENT"

    internal fun requestAPI(
        functionName: String,
        options: JsonObject?,
        transactionId: String,
        preference: WebPreference
    ) {
        val requestData = JsonObject()
        val extra = JsonObject()
        var returnMsg = JsonObject()
        requestData.addProperty("functionName", functionName)
        when (functionName) {
            FunctionName.GET_SERVER_TOKEN -> {
                extra.addProperty(
                    "accessToken",
                    preference.preference.getString("token", "")
                )
                returnMsg = makeReturnMsg(200, resultMsg = "Success", extra, transactionId = transactionId)
            }
            FunctionName.START_ACTIVITY -> {
                if (options != null) {
                    if (options.has("target")) {
                        requestData.addProperty("target", options.get("target").asString)
                    }
                }
                returnMsg = makeReturnMsg(resultMsg = "Request Success", extra = extra, transactionId = transactionId)
            }
            FunctionName.SET_BACK_BUTTON_RECEIVE -> {
                if (options != null) {
                    if (options.has("target")) {
                        requestData.addProperty("target", options.get("target").asString)
                    }
                    if (options.has("isUpdated")) {
                        requestData.addProperty("isUpdated", options.get("isUpdated").asBoolean)
                    }
                }
                returnMsg = makeReturnMsg(resultMsg = "Request Success", extra = extra, transactionId = transactionId)
            }
            FunctionName.CLOSE_WEB_VIEW -> {
                returnMsg = makeReturnMsg(resultMsg = "Request Success", extra = extra, transactionId = transactionId)
            }
        }
        channel.trySend(requestData)
        jsInterface.onJavaScriptResponse(returnMsg)
    }

    fun sendNativeEvent(functionName: String, extra: JsonObject?) {
        var returnMsg = JsonObject()
        when (functionName) {
            FunctionName.ON_BACK_BUTTON_PRESSED -> {
                returnMsg = makeReturnMsg(eventType = BACK_BUTTON_EVENT)
            }
        }
        jsInterface.onJavaScriptResponse(returnMsg)
    }

    object FunctionName {
        const val GET_SERVER_TOKEN = "getServerToken"
        const val START_ACTIVITY = "startActivity"
        const val SET_BACK_BUTTON_RECEIVE = "setBackButtonReceive"
        const val ON_BACK_BUTTON_PRESSED = "onBackButtonPressed"
        const val CLOSE_WEB_VIEW = "closeWebView"
    }

    /** Web 에게 전달할 callback 생성 **/
    private fun makeReturnMsg(
        resultCode: Int? = 200,
        resultMsg: String? = "Success",
        extra: JsonObject? = null,
        eventType: String? = CALLBACK_EVENT,
        transactionId: String? = ""
    ): JsonObject {
        return JsonObject().apply {
            addProperty("result_cd", resultCode)
            addProperty("result_msg", resultMsg)
            add("extra", extra)
            addProperty("eventType", eventType)
            addProperty("transactionId", transactionId)
        }
    }
}