package com.seniorproject.src.user.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seniorproject.appModules.network.errors.ErrorStates
import com.seniorproject.appModules.storage.SharedPreferencesStorage
import com.seniorproject.appModules.storage.SharedPreferencesStorage.Companion.LECTURER_SESSION
import com.seniorproject.appModules.storage.Storage
import com.seniorproject.src.user.UserRepository
import com.seniorproject.src.user.model.data.request.AdminModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AdminViewModel @Inject constructor(private val userRepository: UserRepository, private val storage: Storage): ViewModel() {
    init {
        Log.i(ADMIN_VIEW_MODEL, "AdminViewModel created!!")
    }
    override fun onCleared() {
        super.onCleared()
        Log.i(ADMIN_VIEW_MODEL, "LoginViewModel destroyed!!")
    }

    private val _queryStatus: MutableLiveData<ErrorStates> by lazy {
        MutableLiveData()
    }
    val queryStatus: LiveData<ErrorStates>
        get() = _queryStatus

    fun setLecturerSession( sessionToken: String ) = storage.setString(LECTURER_SESSION, sessionToken )


    fun onAdmin( adminModel: AdminModel): Observable<Unit>
    {

        val request = AdminModel(
            email = adminModel.email,
            password = adminModel.password
        )
        return userRepository.onAdmin(request)
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
        const val ADMIN_VIEW_MODEL = "AdminViewModel"
    }

}