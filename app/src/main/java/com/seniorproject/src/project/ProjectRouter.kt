package com.seniorproject.src.project
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.seniorproject.appModules.router.Router
import com.seniorproject.src.user.Admin
import com.seniorproject.src.user.Login
import com.seniorproject.src.user.Register
import com.seniorproject.src.user.UserNavigationStatus
import com.seniorproject.src.user.view.login.AdminPresenter
import com.seniorproject.src.user.view.login.LoginPresenter
import com.seniorproject.src.user.view.register.RegisterPresenter
import javax.inject.Inject

sealed class ProjectNavigationStatus


class ProjectRouter @Inject constructor(): Router<ProjectNavigationStatus> {
    override fun navigationLogic(
        activity: AppCompatActivity,
        navigationStatus: ProjectNavigationStatus,
        containerViewId: Int,
        bundle: Bundle?
    ) {
        when (navigationStatus) {

        }
    }

    override fun navigationLogicOnBackPressed(
        activity: AppCompatActivity,
        activeScreen: Fragment,
        containerViewId: Int,
    ) {
        when(activeScreen)
        {
        }
    }



}
