package com.mashup.zuzu.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProofAuth(
    val accessToken: String,
    val refreshToken: String,
    val user: User
)