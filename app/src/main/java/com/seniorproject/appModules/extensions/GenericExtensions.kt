package com.seniorproject.appModules.extensions

import android.util.Log
import com.seniorproject.appModules.network.errors.ErrorStates

fun < ErrorLocalState: ErrorStates>String.toErrorState(localErrorState: Class<ErrorLocalState> ): ErrorLocalState {
    val instance = Class.forName( this ).asSubclass( localErrorState )
    try {
        return instance.kotlin.objectInstance!!
    }
    catch (exc: Exception) {
        Log.d( "ERROR_ON_CONVERT", exc.stackTraceToString() )
        throw Exception( "Unsupported Class type, please check local interceptor configuration." )
    }

}