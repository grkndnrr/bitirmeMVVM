package com.seniorproject.core.dm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seniorproject.core.base.AppViewModelFactory
import com.seniorproject.core.da.ViewModelKey
import com.seniorproject.src.user.view.login.AdminViewModel
import com.seniorproject.src.user.view.login.LoginViewModel
import com.seniorproject.src.user.view.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun provideLoginViewModel(loginViewModel: LoginViewModel): ViewModel

   @Binds
   @IntoMap
   @ViewModelKey(RegisterViewModel::class)
   internal abstract fun provideRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AdminViewModel::class)
    internal abstract fun provideAdminViewModel(AdminViewModel: AdminViewModel): ViewModel

}