package com.abdulkarim.mvvm_retrofit_room.di

import android.app.Application
import androidx.room.Room
import com.abdulkarim.mvvm_retrofit_room.repository.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "post.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostDao(db: AppDatabase) = db.getPostDao()

}