package com.seniorproject.src.user

import com.seniorproject.appModules.helpers.observable
import com.google.gson.Gson
import com.seniorproject.appModules.network.errors.ErrorStates
import com.seniorproject.src.user.model.UserApi
import com.seniorproject.src.user.model.data.request.AdminModel
import com.seniorproject.src.user.model.data.request.LoginModel
import com.seniorproject.src.user.model.data.request.RegisterModel
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor( private val api: UserApi ) {
    val gson: Gson by lazy {
        Gson()
    }
    fun onLogin(loginModel: LoginModel): Observable<ErrorStates> {
        return observable(
            api::login, loginModel, gson
        )
    }
    fun onRegister(registerModel: RegisterModel): Observable<ErrorStates> {
        return observable(
            api::register, registerModel, gson
        )
    }
    fun onAdmin(adminModel: AdminModel): Observable<ErrorStates> {
        return observable(
            api::admin, adminModel, gson
        )
    }
}