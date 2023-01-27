package com.seniorproject.appModules.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName( "message" )
    val message: String,
    @Expose(deserialize = false)
    var type: String = "Any"
)
