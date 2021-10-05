package com.abdulkarim.mvvm_retrofit_room.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdulkarim.mvvm_retrofit_room.repository.model.Post

@Database(entities = [Post::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

}