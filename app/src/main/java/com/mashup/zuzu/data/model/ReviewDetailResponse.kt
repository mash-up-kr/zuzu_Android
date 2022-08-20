package com.mashup.zuzu.data.model

import com.google.gson.annotations.SerializedName

data class ReviewDetailResponse(
    @SerializedName("weather")
    val weather: String = "Snowys",
    @SerializedName("time")
    val time: String = "Midnight",
    @SerializedName("companion")
    val companion: String = "Friends",
    @SerializedName("mood")
    val mood: String = "Crazy",
    @SerializedName("spot")
    val spot: String = "1",
    @SerializedName("is_heavy")
    val isHeavy: IsHeavy = IsHeavy(light = 6, heavy = 1),
    @SerializedName("is_bitter")
    val isBitter: IsBitter = IsBitter(sweet =  0, bitter = 7),
    @SerializedName("is_strong")
    val isStrong: IsStrong = IsStrong(mild = 0, strong = 7),
    @SerializedName("is_burning")
    val isBurning: IsBurning = IsBurning(smooth = 0, burning = 7),
    @SerializedName("taste")
    val taste: List<Taste> = listOf(Taste(tasteName = "Creamy", percent = "42%")),
    @SerializedName("pairing")
    val pairing: List<String> = listOf("Fried")
)

data class IsHeavy(
    @SerializedName("Light")
    val light: Int,
    @SerializedName("Heavy")
    val heavy: Int
)

data class IsBitter(
    @SerializedName("Sweet")
    val sweet: Int,
    @SerializedName("Bitter")
    val bitter: Int
)

data class IsStrong(
    @SerializedName("Mild")
    val mild: Int,
    @SerializedName("Strong")
    val strong: Int
)

data class IsBurning(
    @SerializedName("Smooth")
    val smooth: Int,
    @SerializedName("Burning")
    val burning: Int
)

data class Taste(
    @SerializedName("taste_name")
    val tasteName: String,
    @SerializedName("percent")
    val percent: String
)