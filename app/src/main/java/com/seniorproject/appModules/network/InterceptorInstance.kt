package com.seniorproject.appModules.network

import android.util.Log
import com.seniorproject.appModules.network.response.BaseErrorResponse
import com.google.gson.Gson
import com.seniorproject.appModules.network.errors.*
import com.seniorproject.appModules.network.errors.UnknownError
import okhttp3.Interceptor
import okhttp3.Response

class InterceptorInstance: Interceptor{
    private val errorCodes: Map< Int, ErrorStates> by lazy {
        mapOf(
            4001 to UnAuthorizedToken,
            4002 to JWTExpired,
            4003 to EmailNotConfirmed,
            4004 to StudentNotFound,
            4005 to LecturerNotFound,
            4006 to StudentExists
        )
    }
    val gson: Gson by lazy {
        Gson()
    }
    private fun initializeErrorInstance( type: () -> ErrorStates)= type( )
    private val<T: Any> T.kClass: String
        get() = javaClass.name

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val catchResponse = chain.proceed( request )

        return when( catchResponse.code ){
            200 -> catchResponse
            400 -> {
                Log.d( "Intercepted Error", catchResponse.body!!.toString() )
                val baseErrorResponse = gson.fromJson( catchResponse.body!!.string(), BaseErrorResponse::class.java )
                val instance = initializeErrorInstance { errorCodes[ baseErrorResponse.code ]!! }
                val errorInstance = gson.toJson( BaseErrorResponse( code = baseErrorResponse.code, message = baseErrorResponse.message, instance.kClass ) )
                throw NetworkError( errorInstance )
            }
            else -> throw NetworkError( gson.toJson( BaseErrorResponse( code = catchResponse.code, message = "UnknownError", type = UnknownError.kClass ) ) )
        }
    }
}