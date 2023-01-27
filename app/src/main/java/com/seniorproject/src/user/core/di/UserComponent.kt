package com.seniorproject.src.user.core.di

import com.seniorproject.core.dm.CompositeModule
import com.seniorproject.core.dm.RouterModule
import com.seniorproject.src.user.UserBridge
import com.seniorproject.src.user.core.ds.UserScope
import com.seniorproject.src.user.view.login.AdminPresenter
import com.seniorproject.src.user.view.login.LoginPresenter
import com.seniorproject.src.user.view.register.RegisterPresenter
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [ RouterModule::class, CompositeModule::class ])
interface UserComponent {
    @Subcomponent.Factory
    interface Factory
    {
        fun create(): UserComponent
    }

    fun inject(activity: UserBridge)
    fun inject(fragment: LoginPresenter)
    fun inject(fragment: RegisterPresenter)
    fun inject(fragment: AdminPresenter)

}