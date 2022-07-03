package com.mashup.zuzu.bridge

import android.content.SharedPreferences

interface WebPreference {
    val preference: SharedPreferences
    fun <T : Any> apply(key: String, value: T?)
    fun <T : Any> commit(key: String, value: T?)
    fun <T : Any> get(key: String, defaultValue: T): T
}
