package com.human_developing_soft.productcalc.di

import android.content.Context
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelFactoryModule::class])
interface ApplicationComponent {

    fun getContext(): Context

    fun getHttpClient(): OkHttpClient

    fun getRemoteConfig(): FirebaseRemoteConfig

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context,
            @BindsInstance
            remoteConfig: FirebaseRemoteConfig
        ): ApplicationComponent
    }
}