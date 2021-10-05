package com.abdulkarim.mvvm_retrofit_room.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdulkarim.mvvm_retrofit_room.repository.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPost(Posts: List<Post>)

    @Query("SELECT * FROM post_table")
    suspend fun loadAllPost(): List<Post>

}