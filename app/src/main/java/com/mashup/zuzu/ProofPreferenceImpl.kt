package com.mashup.zuzu

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.mashup.zuzu.bridge.ProofPreference

class ProofPreferenceImpl constructor(
    private val applicationContext: Context,
    private val applicationId: String
) : ProofPreference {

    override val preference: SharedPreferences
        get() = applicationContext.getSharedPreferences("$applicationId.pref", Context.MODE_PRIVATE)

    override fun <T : Any> apply(key: String, value: T?) {
        when (value) {
            is String? -> apply { it.putString(key, value) }
            is Int -> apply { it.putInt(key, value) }
            is Boolean -> apply { it.putBoolean(key, value) }
            is Float -> apply { it.putFloat(key, value) }
            is Long -> apply { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    override fun <T : Any> commit(key: String, value: T?) {
        return when (value) {
            is String? -> commit { it.putString(key, value) }
            is Int -> commit { it.putInt(key, value) }
            is Boolean -> commit { it.putBoolean(key, value) }
            is Float -> commit { it.putFloat(key, value) }
            is Long -> commit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> preference.getString(key, defaultValue) as T
            is Int -> preference.getInt(key, defaultValue) as T
            is Boolean -> preference.getBoolean(key, defaultValue) as T
            is Float -> preference.getFloat(key, defaultValue) as T
            is Long -> preference.getLong(key, defaultValue) as T
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    private inline fun apply(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = preference.edit()
        operation(editor)
        editor.apply()
    }

    @SuppressLint("ApplySharedPref")
    private inline fun commit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = preference.edit()
        operation(editor)
        editor.commit()
    }
}
