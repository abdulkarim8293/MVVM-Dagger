package com.abdulkarim.mvvm_retrofit_room.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.LinearLayoutManager
import com.abdulkarim.mvvm_retrofit_room.R
import com.abdulkarim.mvvm_retrofit_room.constant.MyApplication
import com.abdulkarim.mvvm_retrofit_room.constant.utils.connectivity.ConnectivityLiveData
import com.abdulkarim.mvvm_retrofit_room.databinding.ActivityMainBinding
import com.abdulkarim.mvvm_retrofit_room.repository.model.Post
import com.abdulkarim.mvvm_retrofit_room.view.adapter.PostListAdapter
import com.abdulkarim.mvvm_retrofit_room.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), PostListAdapter.OnItemClicked {

    @Inject lateinit var mainActivityViewModel: MainActivityViewModel
    @Inject lateinit var postListAdapter: PostListAdapter
    @Inject lateinit var hello:String

    private lateinit var binding: ActivityMainBinding



    private lateinit var connectivityLiveData: ConnectivityLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainActivityComponent.factory().create((application as MyApplication).getAppComponent()).inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        postListAdapter.setItemClickedListener(this)
        connectivityLiveData= ConnectivityLiveData(application)

        mainActivityViewModel.fetchPost()

        mainActivityViewModel.getPost().observe(this,{ post ->
            postListAdapter.setPostItems(post)
            binding.progress.visibility = GONE

            lifecycleScope.launch {
                mainActivityViewModel.insert(post)
            }

        })

        Toast.makeText(applicationContext, hello, Toast.LENGTH_SHORT).show()


        mainActivityViewModel.getStatus().observe(this,{
            status -> Toast.makeText(this@MainActivity, status, Toast.LENGTH_SHORT).show()
            binding.progress.visibility = VISIBLE

            lifecycleScope.launch {
                mainActivityViewModel.fetchLocalPost()

            }
            mainActivityViewModel.getPostFromRoom().observe(this,{ post ->
                postListAdapter.setPostItems(post)
                binding.progress.visibility = GONE

            })

        })

        binding.postRv.adapter = postListAdapter
        binding.postRv.layoutManager = LinearLayoutManager(this)


        connectivityLiveData.observe(this, Observer {isAvailable->
            if (isAvailable){
                Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Not connected", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onItemClickable(post: Post) {
        Toast.makeText(this, post.title, Toast.LENGTH_SHORT).show()
    }


}