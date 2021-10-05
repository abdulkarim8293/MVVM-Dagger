package com.abdulkarim.mvvm_retrofit_room.di

import com.abdulkarim.mvvm_retrofit_room.constant.AppConstant
import com.abdulkarim.mvvm_retrofit_room.repository.network.RetrofitServiceInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AppConstant.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofitServiceInterface(retrofit: Retrofit):RetrofitServiceInterface{
        return retrofit.create(RetrofitServiceInterface::class.java)
    }

}
