package com.seniorproject.appModules.network.response

import com.seniorproject.appModules.network.generics.ResponseDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("status") val status: Int = 0,
    @Expose(deserialize = false) // deserialize is this filed is not required
    @SerializedName("error") val error: String = "",
    @SerializedName("debug_message") val debugMessage: String = "",
    @SerializedName("display_message") val displayMessage: String = "",
    @SerializedName("Http_code") val httpCode: Int = 0,
    //@SerializedName("data") val data:

    //@SerializedName("error_type") val errorType: String = "",
    //@SerializedName("message") val message: String = "",
): ResponseDto
