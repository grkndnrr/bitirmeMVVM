package com.seniorproject.core.dm


import com.seniorproject.appModules.storage.SharedPreferencesStorage
import com.seniorproject.appModules.storage.Storage
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class StorageModule {
    @Binds
    @Singleton
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage

}