package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName
import com.mashup.zuzu.data.response.model.Result
/**
 * @Created by 김현국 2022/08/23
 */
data class GetDrinksEvaluationResponse(

    @SerializedName("result")
    val result: Result?
)
