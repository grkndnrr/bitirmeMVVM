package com.seniorproject.src.user.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seniorproject.appModules.network.errors.ErrorStates
import com.seniorproject.appModules.storage.SharedPreferencesStorage.Companion.STUDENT_SESSION
import com.seniorproject.appModules.storage.Storage
import com.seniorproject.src.user.UserRepository
import com.seniorproject.src.user.model.data.request.LoginModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository, private val storage: Storage): ViewModel() {

    init {
        Log.i(LOGIN_VIEW_MODEL, "LoginViewModel created!!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(LOGIN_VIEW_MODEL, "LoginViewModel destroyed!!")
    }

    private val _queryStatus: MutableLiveData<ErrorStates> by lazy {
        MutableLiveData()
    }

    val queryStatus: LiveData<ErrorStates>
        get() = _queryStatus

    fun setStudentSession( sessionToken: String ) = storage.setString( STUDENT_SESSION, sessionToken )

    fun onLogin( loginModel: LoginModel ): Observable<Unit>
    {
        val request = LoginModel(
            studentId = loginModel.studentId,
            password = loginModel.password
        )
        return userRepository.onLogin(request)
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
        const val LOGIN_VIEW_MODEL = "LoginViewModel"
    }
}