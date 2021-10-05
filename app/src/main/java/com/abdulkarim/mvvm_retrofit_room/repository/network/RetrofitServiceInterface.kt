package com.abdulkarim.mvvm_retrofit_room.repository.network

import com.abdulkarim.mvvm_retrofit_room.repository.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServiceInterface {

    @GET("posts")
    fun getPostResponse(): Call<List<Post>>

}