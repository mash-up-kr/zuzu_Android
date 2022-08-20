package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.model.ProofAuth
import com.mashup.zuzu.data.response.GetProofAuthResponse

fun proofAuthResponseToModel(
    getProofAuthResponse: GetProofAuthResponse
): ProofAuth {
    return ProofAuth(
        accessToken = getProofAuthResponse.accessToken,
        refreshToken = getProofAuthResponse.refreshToken,
        user = getProofAuthResponse.user
    )
}