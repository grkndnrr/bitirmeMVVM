package com.seniorproject.src.user.view.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seniorproject.appModules.network.errors.ErrorStates
import com.seniorproject.appModules.storage.SharedPreferencesStorage
import com.seniorproject.appModules.storage.Storage
import com.seniorproject.src.user.UserRepository
import com.seniorproject.src.user.model.data.request.LoginModel
import com.seniorproject.src.user.model.data.request.RegisterModel
import com.seniorproject.src.user.view.login.LoginViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val  userRepository: UserRepository, private val storage: Storage):ViewModel(){


    init {
        Log.i(REGISTER_VIEW_MODEL,"RegisterViewModel Created!!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(REGISTER_VIEW_MODEL, "LoginViewModel destroyed!!")
    }

    private val _queryStatus: MutableLiveData<ErrorStates> by lazy {
        MutableLiveData()
    }

    val queryStatus: LiveData<ErrorStates>
        get() = _queryStatus

    fun setStudentSession( sessionToken: String ) = storage.setString(SharedPreferencesStorage.STUDENT_SESSION, sessionToken )

    fun onRegister( registerModel: RegisterModel): Observable<Unit>
    {
        val request = RegisterModel(
            studentId = registerModel.studentId,
            password = registerModel.password,
            studentType = registerModel.studentType
        )
        return userRepository.onRegister(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { repositoryStatus ->
                _queryStatus.postValue( repositoryStatus )
            }
            .doOnError {
                Log.d( "ERROR", it.toString() )
            }
    }






    companion object {
        const val REGISTER_VIEW_MODEL = "RegisterViewModel"
    }
}