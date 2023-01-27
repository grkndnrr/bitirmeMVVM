package com.seniorproject.src.project

import android.os.Bundle
import com.seniorproject.R
import com.seniorproject.appModules.bridge.Bridge
import com.seniorproject.core.base.ApplicationGraph
import com.seniorproject.src.project.core.di.ProjectComponent
import com.seniorproject.src.project.core.ds.ProjectScope

@ProjectScope
class ProjectBridge: Bridge<ProjectComponent, ProjectNavigationStatus>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        component = (application as ApplicationGraph).applicationGraphComponent.projectComponent().create()
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}