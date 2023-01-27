package com.seniorproject.src.project.core.di

import com.seniorproject.core.dm.CompositeModule
import com.seniorproject.core.dm.RouterModule
import com.seniorproject.src.project.ProjectBridge
import com.seniorproject.src.project.core.ds.ProjectScope
import dagger.Subcomponent


@ProjectScope
@Subcomponent(modules = [ RouterModule::class, CompositeModule::class ])
interface ProjectComponent {
    @Subcomponent.Factory
    interface Factory
    {
        fun create(): ProjectComponent
    }
    fun inject(activity: ProjectBridge)

    //injections
}