package com.seniorproject.src.user.model.data.request

import com.google.gson.annotations.SerializedName
import com.seniorproject.appModules.network.generics.RequestDto

data class LoginModel(
    @SerializedName("studentId")
    val studentId: String,
    @SerializedName("password")
    val password: String
): RequestDto
