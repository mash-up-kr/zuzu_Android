package com.mashup.zuzu.ui.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

val Primary50 = Color(0xFFEDEDFF)
val Primary100 = Color(0xFFC4BEFF)
val Primary200 = Color(0xFF9685FF)
val Primary300 = Color(0xFF6748E3)
val Primary400 = Color(0xFF4F17C5)
val Primary500 = Color(0xFF4110AA)

@Stable
class Colors(
    primary50: Color,
    primary100: Color,
    primary200: Color,
    primary300: Color,
    primary400: Color,
    primary500: Color,
    isLight: Boolean
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
    var isLight by mutableStateOf(isLight, structuralEqualityPolicy())
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
        isLight: Boolean = this.isLight
    ): Colors = Colors(
        primary50,
        primary100,
        primary200,
        primary300,
        primary400,
        primary500,
        isLight
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
): Colors = Colors(
    primary50,
    primary100,
    primary200,
    primary300,
    primary400,
    primary500,
    isLight = true
)

fun darkColors(
    primary50: Color = Primary50,
    primary100: Color = Primary100,
    primary200: Color = Primary200,
    primary300: Color = Primary300,
    primary400: Color = Primary400,
    primary500: Color = Primary500,
): Colors = Colors(
    primary50,
    primary100,
    primary200,
    primary300,
    primary400,
    primary500,
    isLight = false
)

val LocalProofColor = staticCompositionLocalOf {
    lightColors()
}