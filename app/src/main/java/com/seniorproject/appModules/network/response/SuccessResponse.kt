package com.seniorproject.appModules.network.response

import com.seniorproject.appModules.network.errors.ErrorStates

data class SuccessResponse < out DataType >(
    val data: DataType?
) : ErrorStates()