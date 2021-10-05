package com.abdulkarim.mvvm_retrofit_room.view

import com.abdulkarim.mvvm_retrofit_room.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
object MainActivityModule {
    @ActivityScope
    @Provides
    fun provideHelloWorldString():String = "Hello World"
}