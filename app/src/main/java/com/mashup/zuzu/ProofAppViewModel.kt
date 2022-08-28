package com.mashup.zuzu

import androidx.lifecycle.ViewModel
import com.mashup.zuzu.bridge.ProofPreference
import com.mashup.zuzu.util.Key
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/28
 */

@HiltViewModel
class ProofAppViewModel @Inject constructor(
    private val proofPreference: ProofPreference
) : ViewModel() {

    fun checkAccount(): Boolean {
        val result = proofPreference.get(Key.Preference.ACCESS_TOKEN, "")

        return result.isNotEmpty()
    }
}
