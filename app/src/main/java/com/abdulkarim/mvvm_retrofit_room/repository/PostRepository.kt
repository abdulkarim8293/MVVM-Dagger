package com.abdulkarim.mvvm_retrofit_room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abdulkarim.mvvm_retrofit_room.repository.model.Post
import com.abdulkarim.mvvm_retrofit_room.repository.network.RetrofitServiceInterface
import com.abdulkarim.mvvm_retrofit_room.repository.room.PostDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostRepository  @Inject constructor(
    private val retrofitServiceInterface: RetrofitServiceInterface,
    private val postDao: PostDao
){
    private val _posts = MutableLiveData<List<Post>>()
    val posts:LiveData<List<Post>> get() = _posts

    fun getPostFromNetwork(){
        val call = retrofitServiceInterface.getPostResponse()
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    _posts.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                _status.value = t.message
            }

        })

    }

    private val _status = MutableLiveData<String>()
    val status:LiveData<String> get() = _status


    suspend fun insert(postList:List<Post>){
        postDao.insertAllPost(postList)
    }

    private val _localPosts = MutableLiveData<List<Post>>()
    val localPosts:LiveData<List<Post>> get() = _localPosts

    suspend fun getPostFromRoom(){
        _localPosts.value = postDao.loadAllPost()
    }

}