package com.seniorproject.core.di

import android.content.Context
import com.seniorproject.core.dm.NetworkModule
import com.seniorproject.core.dm.StorageModule
import com.seniorproject.core.dm.ViewModelModule
import com.seniorproject.src.project.core.di.ProjectComponent
import com.seniorproject.src.project.core.di.ProjectProvider
import com.seniorproject.src.user.core.di.UserComponent
import com.seniorproject.src.user.core.di.UserProvider

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    StorageModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    UserProvider::class,
    ProjectProvider::class,

])
interface ApplicationGraphComponent {
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): ApplicationGraphComponent
    }
    fun userComponent(): UserComponent.Factory
    fun projectComponent(): ProjectComponent.Factory

//@LogicFactoryProvider
}

