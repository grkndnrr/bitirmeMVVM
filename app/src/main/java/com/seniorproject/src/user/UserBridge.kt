package com.seniorproject.src.user

import android.os.Bundle
import com.seniorproject.R
import com.seniorproject.appModules.bridge.Bridge
import com.seniorproject.core.base.ApplicationGraph
import com.seniorproject.src.user.core.di.UserComponent
import com.seniorproject.src.user.core.ds.UserScope
import com.seniorproject.src.user.view.login.AdminPresenter
import com.seniorproject.src.user.view.login.LoginPresenter
import com.seniorproject.src.user.view.register.RegisterPresenter

@UserScope
class UserBridge: Bridge<UserComponent, UserNavigationStatus>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        component = (application as ApplicationGraph).applicationGraphComponent.userComponent().create()
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startNavigation { LoginPresenter() }
    }

}