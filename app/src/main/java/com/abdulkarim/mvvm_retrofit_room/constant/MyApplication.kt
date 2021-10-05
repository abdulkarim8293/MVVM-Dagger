package com.abdulkarim.mvvm_retrofit_room.constant

import android.app.Application
import com.abdulkarim.mvvm_retrofit_room.di.AppComponent
import com.abdulkarim.mvvm_retrofit_room.di.DaggerAppComponent


class MyApplication : Application () {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
    }
    fun getAppComponent() : AppComponent{
        return component
    }

}