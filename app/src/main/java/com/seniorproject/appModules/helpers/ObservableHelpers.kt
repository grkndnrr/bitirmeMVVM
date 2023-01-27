package com.seniorproject.appModules.helpers

import android.util.Log
import com.seniorproject.appModules.extensions.toErrorState
import com.seniorproject.appModules.network.errors.ErrorStates
import com.seniorproject.appModules.network.generics.RequestDto
import com.seniorproject.appModules.network.response.BaseErrorResponse
import com.seniorproject.appModules.network.generics.ResponseDto
import com.seniorproject.appModules.network.response.SuccessResponse
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.reflect.KFunction1

inline fun < reified ErrorState: ErrorStates, reified DataTransferObject: RequestDto, reified ResponseDataTransferObject: ResponseDto> observable (apiEndpoint: KFunction1<DataTransferObject, Observable<ResponseDataTransferObject>>, dto: DataTransferObject, gsonSerializer: Gson): Observable<ErrorState> {
    return apiEndpoint( dto )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            SuccessResponse( data = it ) as ErrorState
        }
        .onErrorReturn { error ->
            Log.d( "Observed Error", error.toString() )
            val errorParsed = gsonSerializer.fromJson( error.message, BaseErrorResponse::class.java )
            errorParsed.type.toErrorState( ErrorState::class.java )
        }
}
