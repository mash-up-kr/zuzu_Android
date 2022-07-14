package com.mashup.zuzu.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mashup.zuzu.R

val pretended = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_medium, FontWeight.Normal),
    Font(R.font.pretendard_thin, FontWeight.Light)
)

val proofTypography = ProofTypography(
    headingXL = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 34.sp
    ),

    headingL = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 34.sp
    ),

    headingM = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 34.sp
    ),

    headingS = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 34.sp
    ),

    headingXS = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 34.sp
    ),

    bodyXL = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 32.sp
    ),

    bodyL = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 32.sp
    ),

    bodyL600 = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 25.sp
    ),

    bodyM = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 32.sp
    ),

    bodyS = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 32.sp
    ),

    bodyS600 = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 20.sp
    ),

    bodyXS = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 32.sp
    ),

    bodyXS600 = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 19.sp
    ),

    body2XS600 = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 17.sp
    ),

    body3XS = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 32.sp
    ),

    buttonL = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),

    buttonM = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Thin,
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),

    buttonS = TextStyle(
        fontFamily = pretended,
        fontWeight = FontWeight.Thin,
        fontSize = 13.sp,
        lineHeight = 22.sp
    ),
)

@Immutable
data class ProofTypography(
    val headingXL: TextStyle,
    val headingL: TextStyle,
    val headingM: TextStyle,
    val headingS: TextStyle,
    val headingXS: TextStyle,
    val bodyXL: TextStyle,
    val bodyL: TextStyle,
    val bodyL600: TextStyle,
    val bodyM: TextStyle,
    val bodyS: TextStyle,
    val bodyS600: TextStyle,
    val bodyXS: TextStyle,
    val bodyXS600: TextStyle,
    val body2XS600: TextStyle,
    val body3XS: TextStyle,
    val buttonL: TextStyle,
    val buttonM: TextStyle,
    val buttonS: TextStyle,
)

val LocalProofTypography = staticCompositionLocalOf {
    ProofTypography(
        headingXL = TextStyle.Default,
        headingL = TextStyle.Default,
        headingM = TextStyle.Default,
        headingS = TextStyle.Default,
        headingXS = TextStyle.Default,
        bodyXL = TextStyle.Default,
        bodyL = TextStyle.Default,
        bodyL600 = TextStyle.Default,
        bodyM = TextStyle.Default,
        bodyS = TextStyle.Default,
        bodyS600 = TextStyle.Default,
        bodyXS = TextStyle.Default,
        bodyXS600 = TextStyle.Default,
        body2XS600 = TextStyle.Default,
        body3XS = TextStyle.Default,
        buttonL = TextStyle.Default,
        buttonM = TextStyle.Default,
        buttonS = TextStyle.Default
    )
}
