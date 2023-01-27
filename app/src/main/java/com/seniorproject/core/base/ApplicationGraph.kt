package com.seniorproject.core.base

import android.app.Application
import com.seniorproject.core.di.ApplicationGraphComponent
import com.seniorproject.core.di.DaggerApplicationGraphComponent

open class ApplicationGraph: Application() {
    val applicationGraphComponent: ApplicationGraphComponent by lazy {
        DaggerApplicationGraphComponent.factory().create(applicationContext)
    }
}