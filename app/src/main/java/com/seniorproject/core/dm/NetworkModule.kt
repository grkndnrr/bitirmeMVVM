package com.seniorproject.core.dm

import com.seniorproject.appModules.network.InterceptorInstance
import com.google.gson.GsonBuilder
import com.seniorproject.src.project.model.data.ProjectApi
import com.seniorproject.src.user.model.UserApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val mainDomain = "http://34.243.54.223:4000/v1/"
    private fun < ApiInstance: Any >builder( api: Class<ApiInstance> ) = Retrofit.Builder()
        .baseUrl( mainDomain )
        .client( interceptorBuilder( InterceptorInstance() ) )
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(api)

    private fun interceptorBuilder( interceptor: Interceptor ) =
        OkHttpClient().newBuilder()
            .addInterceptor( interceptor )
            .build()


    @Provides
    @Singleton
    fun provideUserNetwork( ): UserApi = builder( UserApi::class.java )

    @Provides
    @Singleton
    fun provideProjectNetwork( ): ProjectApi = builder( ProjectApi::class.java )

}
