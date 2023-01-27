package com.seniorproject.src.user.model

import com.seniorproject.src.user.model.data.request.AdminModel
import com.seniorproject.src.user.model.data.request.LoginModel
import com.seniorproject.src.user.model.data.request.RegisterModel
import com.seniorproject.src.user.model.data.response.AdminResponse
import com.seniorproject.src.user.model.data.response.LoginResponse
import com.seniorproject.src.user.model.data.response.RegisterResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {
    @Headers("Content-Type:application/json")
    @POST("student/login")
    fun login(@Body loginModel: LoginModel): Observable<LoginResponse>

    @Headers("Content-Type:application/json")
    @POST("student/")
    fun register(@Body registerModel: RegisterModel): Observable<RegisterResponse>

    @Headers("Content-Type:application/json")
    @POST("lecturer/login/")
    fun admin(@Body adminModel: AdminModel): Observable<AdminResponse>

}