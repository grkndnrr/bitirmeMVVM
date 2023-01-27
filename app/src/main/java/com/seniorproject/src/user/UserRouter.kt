package com.seniorproject.src.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.seniorproject.appModules.router.Router
import com.seniorproject.src.user.view.login.AdminPresenter
import com.seniorproject.src.user.view.login.LoginPresenter
import com.seniorproject.src.user.view.register.RegisterPresenter
import javax.inject.Inject

sealed class UserNavigationStatus
data class Login( val LOGIN: Class< LoginPresenter > = LoginPresenter::class.java ): UserNavigationStatus()
data class Register(val REGISTER: Class<RegisterPresenter> = RegisterPresenter::class.java): UserNavigationStatus()
data class Admin(val ADMIN: Class<AdminPresenter> = AdminPresenter::class.java): UserNavigationStatus()


class UserRouter @Inject constructor(): Router<UserNavigationStatus> {
    override fun navigationLogic(
        activity: AppCompatActivity,
        navigationStatus: UserNavigationStatus,
        containerViewId: Int,
        bundle: Bundle?
    ) {
        when(navigationStatus)
        {
            is Login -> onFragmentChange( activity, containerViewId, navigationStatus.LOGIN, bundle)
            is Register -> onFragmentChange(activity,containerViewId,navigationStatus.REGISTER,bundle)
            is Admin -> onFragmentChange(activity,containerViewId,navigationStatus.ADMIN,bundle)
            //@EndNavigationCaseCheck
        }
    }

    override fun navigationLogicOnBackPressed(
        activity: AppCompatActivity,
        activeScreen: Fragment,
        containerViewId: Int,
    ) {
        when(activeScreen)
        {
            is LoginPresenter -> activity.finishAffinity()
            is AdminPresenter -> onFragmentChange( activity, containerViewId, Login().LOGIN )
            is RegisterPresenter -> onFragmentChange( activity, containerViewId, Login().LOGIN )
        }
    }
}