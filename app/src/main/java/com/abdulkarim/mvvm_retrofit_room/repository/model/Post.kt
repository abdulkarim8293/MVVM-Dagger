package com.abdulkarim.mvvm_retrofit_room.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class Post(
    @ColumnInfo(name = "user_id")
    val userId:Int,
    @PrimaryKey
    @ColumnInfo(name = "post_id")
    val id:Int,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "body")
    val body:String
)