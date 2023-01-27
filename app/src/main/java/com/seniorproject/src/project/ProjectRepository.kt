package com.seniorproject.src.project

import com.google.gson.Gson
import com.seniorproject.src.project.model.data.ProjectApi
import javax.inject.Inject

class ProjectRepository @Inject constructor(private val api: ProjectApi){
    val gson: Gson by lazy {
        Gson()
    }



}