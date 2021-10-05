package com.abdulkarim.mvvm_retrofit_room.di

import android.app.Application
import com.abdulkarim.mvvm_retrofit_room.repository.network.RetrofitServiceInterface
import com.abdulkarim.mvvm_retrofit_room.repository.room.PostDao
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Singleton
@Component(modules = [NetworkModule::class,DatabaseModule::class])
interface AppComponent{
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application):AppComponent
    }
    fun provideApplication():Application

    fun providePostDao(): PostDao
    fun provideRetrofitServiceInterface(): RetrofitServiceInterface
}

