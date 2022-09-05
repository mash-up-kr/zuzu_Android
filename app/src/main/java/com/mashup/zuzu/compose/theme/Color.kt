package com.mashup.zuzu.compose.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

val Primary50 = Color(0xFFEDEDFF)
val Primary100 = Color(0xFFC4BEFF)
val Primary200 = Color(0xFF9685FF)
val Primary300 = Color(0xFF6748E3)
val Primary400 = Color(0xFF4F17C5)
val Primary500 = Color(0xFF4110AA)

val White = Color(0xFFFCFCFF)
val Gray50 = Color(0xFFEFEFF8)
val Gray100 = Color(0xFFD5D8EA)
val Gray200 = Color(0xFFAEB4CA)
val Gray300 = Color(0xFF8D90A9)
val Gray400 = Color(0xFF5D6077)
val Gray500 = Color(0xFF383A4D)
val Gray600 = Color(0xFF2A2C3C)
val Black = Color(0xFF1C1C26)

// FUNCTIONAL
val Green100 = Color(0xFFE6F5C0)
val Green200 = Color(0xFFBAE348)
val Green300 = Color(0xFF96B602)
val Orange100 = Color(0xFFFFD9CF)
val Orange200 = Color(0xFFFF9B81)
val Orange300 = Color(0xFFEF562D)

// Gradient
val GradientPurple = Color(0xFF5646A3)
val GradientBlack = Color(0xFF2A2C3C)

// HorizontalGradient
val HorizontalPurple = Color(0x366748E3)
val HorizontalBlack = Color(0x6748E3)

val BrushColor = Color(0xB3383A4D)

@Stable
class Colors(
    primary50: Color,
    primary100: Color,
    primary200: Color,
    primary300: Color,
    primary400: Color,
    primary500: Color,
    white: Color,
    gray50: Color,
    gray100: Color,
    gray200: Color,
    gray300: Color,
    gray400: Color,
    gray500: Color,
    gray600: Color,
    black: Color,
    isLight: Boolean,
    green100: Color,
    green200: Color,
    green300: Color,
    orange100: Color,
    orange200: Color,
    orange300: Color,
    gradientPurple: Color,
    gradientBlack: Color

) {
    var primary50 by mutableStateOf(primary50, structuralEqualityPolicy())
        internal set
    var primary100 by mutableStateOf(primary100, structuralEqualityPolicy())
        internal set
    var primary200 by mutableStateOf(primary200, structuralEqualityPolicy())
        internal set
    var primary300 by mutableStateOf(primary300, structuralEqualityPolicy())
        internal set
    var primary400 by mutableStateOf(primary400, structuralEqualityPolicy())
        internal set
    var primary500 by mutableStateOf(primary500, structuralEqualityPolicy())
        internal set
    var white by mutableStateOf(white, structuralEqualityPolicy())
        internal set
    var gray50 by mutableStateOf(gray50, structuralEqualityPolicy())
        internal set
    var gray100 by mutableStateOf(gray100, structuralEqualityPolicy())
        internal set
    var gray200 by mutableStateOf(gray200, structuralEqualityPolicy())
        internal set
    var gray300 by mutableStateOf(gray300, structuralEqualityPolicy())
        internal set
    var gray400 by mutableStateOf(gray400, structuralEqualityPolicy())
        internal set
    var gray500 by mutableStateOf(gray500, structuralEqualityPolicy())
        internal set
    var gray600 by mutableStateOf(gray600, structuralEqualityPolicy())
        internal set
    var black by mutableStateOf(black, structuralEqualityPolicy())
        internal set
    var isLight by mutableStateOf(isLight, structuralEqualityPolicy())
        internal set
    var green100 by mutableStateOf(green100, structuralEqualityPolicy())
        internal set
    var green200 by mutableStateOf(green200, structuralEqualityPolicy())
        internal set
    var green300 by mutableStateOf(green300, structuralEqualityPolicy())
        internal set
    var orange100 by mutableStateOf(orange100, structuralEqualityPolicy())
        internal set
    var orange200 by mutableStateOf(orange200, structuralEqualityPolicy())
        internal set
    var orange300 by mutableStateOf(orange300, structuralEqualityPolicy())
        internal set
    var gradientPurple by mutableStateOf(gradientPurple, structuralEqualityPolicy())
        internal set
    var gradientBlack by mutableStateOf(gradientBlack, structuralEqualityPolicy())
        internal set

    /**
     * Returns a copy of this Colors, optionally overriding some of the values.
     */
    fun copy(
        primary50: Color = this.primary50,
        primary100: Color = this.primary100,
        primary200: Color = this.primary200,
        primary300: Color = this.primary300,
        primary400: Color = this.primary400,
        primary500: Color = this.primary500,
        white: Color = this.white,
        gray50: Color = this.gray50,
        gray100: Color = this.gray100,
        gray200: Color = this.gray200,
        gray300: Color = this.gray300,
        gray400: Color = this.gray400,
        gray500: Color = this.gray500,
        gray600: Color = this.gray600,
        black: Color = this.black,
        isLight: Boolean = this.isLight,
        green100: Color = this.green100,
        green200: Color = this.green200,
        green300: Color = this.green300,
        orange100: Color = this.orange100,
        orange200: Color = this.orange200,
        orange300: Color = this.orange300,
        gradientPurple: Color = this.gradientPurple,
        gradientBlack: Color = this.gradientBlack
    ): Colors = Colors(
        primary50,
        primary100,
        primary200,
        primary300,
        primary400,
        primary500,
        white,
        gray50,
        gray100,
        gray200,
        gray300,
        gray400,
        gray500,
        gray600,
        black,
        isLight,
        green100,
        green200,
        green300,
        orange100,
        orange200,
        orange300,
        gradientPurple,
        gradientBlack
    )

    override fun toString(): String {
        return "Colors()"
    }
}

fun lightColors(
    primary50: Color = Primary50,
    primary100: Color = Primary100,
    primary200: Color = Primary200,
    primary300: Color = Primary300,
    primary400: Color = Primary400,
    primary500: Color = Primary500,
    white: Color = White,
    gray50: Color = Gray50,
    gray100: Color = Gray100,
    gray200: Color = Gray200,
    gray300: Color = Gray300,
    gray400: Color = Gray400,
    gray500: Color = Gray500,
    gray600: Color = Gray600,
    black: Color = Black,
    green100: Color = Green100,
    green200: Color = Green200,
    green300: Color = Green300,
    orange100: Color = Orange100,
    orange200: Color = Orange200,
    orange300: Color = Orange300,
    gradientPurple: Color = GradientPurple,
    gradientBlack: Color = GradientBlack
): Colors = Colors(
    primary50,
    primary100,
    primary200,
    primary300,
    primary400,
    primary500,
    white,
    gray50,
    gray100,
    gray200,
    gray300,
    gray400,
    gray500,
    gray600,
    black,
    isLight = true,
    green100,
    green200,
    green300,
    orange100,
    orange200,
    orange300,
    gradientPurple,
    gradientBlack
)

fun darkColors(
    primary50: Color = Primary50,
    primary100: Color = Primary100,
    primary200: Color = Primary200,
    primary300: Color = Primary300,
    primary400: Color = Primary400,
    primary500: Color = Primary500,
    white: Color = White,
    gray50: Color = Gray50,
    gray100: Color = Gray100,
    gray200: Color = Gray200,
    gray300: Color = Gray300,
    gray400: Color = Gray400,
    gray500: Color = Gray500,
    gray600: Color = Gray600,
    black: Color = Black,
    green100: Color = Green100,
    green200: Color = Green200,
    green300: Color = Green300,
    orange100: Color = Orange100,
    orange200: Color = Orange200,
    orange300: Color = Orange300,
    gradientPurple: Color = GradientPurple,
    gradientBlack: Color = GradientBlack
): Colors = Colors(
    primary50,
    primary100,
    primary200,
    primary300,
    primary400,
    primary500,
    white,
    gray50,
    gray100,
    gray200,
    gray300,
    gray400,
    gray500,
    gray600,
    black,
    isLight = false,
    green100,
    green200,
    green300,
    orange100,
    orange200,
    orange300,
    gradientPurple,
    gradientBlack
)

val LocalProofColor = staticCompositionLocalOf {
    lightColors()
}
