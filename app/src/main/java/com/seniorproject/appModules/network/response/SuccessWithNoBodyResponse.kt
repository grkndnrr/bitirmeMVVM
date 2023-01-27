package com.seniorproject.appModules.network.response

import com.seniorproject.appModules.network.generics.ResponseDto
import com.google.gson.annotations.SerializedName

data class SuccessWithNoBodyResponse(
    @SerializedName( "status" )
    val status: Boolean
): ResponseDto