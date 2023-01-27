package com.seniorproject.src.user.model.data.response

import com.google.gson.annotations.SerializedName
import com.seniorproject.appModules.network.generics.RequestDto
import com.seniorproject.appModules.network.generics.ResponseDto
import java.util.*

data class RegisterResponse(
    @SerializedName("token") val token: String = "",
    @SerializedName("expireDate") val expireDate: Date,
    ): ResponseDto
