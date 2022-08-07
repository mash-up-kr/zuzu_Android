package com.mashup.zuzu.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun ProofTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        darkColors()
    } else {
        lightColors()
    }

    CompositionLocalProvider(
        LocalProofTypography provides proofTypography,
        LocalProofColor provides colors
    ) {
        MaterialTheme(
            shapes = Shapes,
            content = content
        )
    }

}

object ProofTheme {
    val typography: ProofTypography
        @Composable
        get() = LocalProofTypography.current

    val color: Colors
        @Composable
        get() = LocalProofColor.current
}