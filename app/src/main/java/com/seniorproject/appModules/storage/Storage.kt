package com.seniorproject.appModules.storage

interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String): String
    fun <T> setModel(key: String, model: T)
    fun <T> getModel(key: String, modelSkeleton: Class<T>): T

}