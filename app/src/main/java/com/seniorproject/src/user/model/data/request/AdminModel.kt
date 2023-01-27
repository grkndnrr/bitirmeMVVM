package com.seniorproject.src.user.model.data.request

import com.google.gson.annotations.SerializedName
import com.seniorproject.appModules.network.generics.RequestDto

data class AdminModel(
    @SerializedName("email")
val email: String,
@SerializedName("password")
val password: String
): RequestDto


