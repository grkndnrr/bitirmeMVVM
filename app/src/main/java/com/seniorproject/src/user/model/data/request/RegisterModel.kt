package com.seniorproject.src.user.model.data.request

import com.google.gson.annotations.SerializedName
import com.seniorproject.appModules.network.generics.RequestDto

data class RegisterModel(
    @SerializedName("studentId")
    val studentId: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("studentType")
    val studentType: Int,// 1. ve 2. örgün

): RequestDto
