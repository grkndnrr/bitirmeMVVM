package com.seniorproject.core.dm

import com.seniorproject.appModules.router.Router
import com.seniorproject.src.project.ProjectNavigationStatus
import com.seniorproject.src.project.ProjectRouter
import com.seniorproject.src.user.UserNavigationStatus
import com.seniorproject.src.user.UserRouter
import com.seniorproject.src.user.core.ds.UserScope
import dagger.Binds
import dagger.Module


//@EndImportLibraries
@Module
abstract class RouterModule {
    @Binds
    @UserScope
    abstract fun provideUserRouter(routerProvider: UserRouter): Router<UserNavigationStatus>
 //@EndRouterBinder
 @Binds
 @UserScope
 abstract fun provideProjectRouter(routerProvider: ProjectRouter): Router<ProjectNavigationStatus>

}

