package com.abdulkarim.mvvm_retrofit_room.view

import com.abdulkarim.mvvm_retrofit_room.di.ActivityScope
import com.abdulkarim.mvvm_retrofit_room.di.AppComponent
import com.abdulkarim.mvvm_retrofit_room.repository.network.RetrofitServiceInterface
import com.abdulkarim.mvvm_retrofit_room.repository.room.PostDao
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [MainActivityModule::class]
)
@ActivityScope
interface MainActivityComponent {
    @Component.Factory
    interface Factory{
        fun create(appComponent: AppComponent): MainActivityComponent
    }
    fun inject(activity: MainActivity)

    fun provideRetrofitServiceInterface(): RetrofitServiceInterface
    fun providePostDao(): PostDao

    fun provideHelloWorldString():String
}