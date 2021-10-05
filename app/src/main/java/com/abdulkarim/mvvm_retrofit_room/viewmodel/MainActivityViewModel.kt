package com.abdulkarim.mvvm_retrofit_room.viewmodel

import androidx.lifecycle.ViewModel
import com.abdulkarim.mvvm_retrofit_room.repository.PostRepository
import com.abdulkarim.mvvm_retrofit_room.repository.model.Post
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val postRepository: PostRepository
) :  ViewModel(){

    fun fetchPost(){
        postRepository.getPostFromNetwork()
    }

    suspend fun fetchLocalPost(){
        postRepository.getPostFromRoom()
    }

    fun getPost() = postRepository.posts
    fun getStatus() = postRepository.status

    suspend fun insert(postList:List<Post>){

        postRepository.insert(postList)
    }

    fun getPostFromRoom() = postRepository.localPosts

}