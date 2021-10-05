package com.abdulkarim.mvvm_retrofit_room.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdulkarim.mvvm_retrofit_room.R
import com.abdulkarim.mvvm_retrofit_room.databinding.ItemPostBinding
import com.abdulkarim.mvvm_retrofit_room.repository.model.Post
import javax.inject.Inject

class PostListAdapter @Inject constructor () : RecyclerView.Adapter<PostListAdapter.MyViewHolder>() {

    private var postList:ArrayList<Post> = arrayListOf()
    private lateinit var onItemClicked:OnItemClicked

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val post = postList[position]

        holder.binding.userIdTv.text = post.userId.toString()
        holder.binding.postIdTv.text = post.id.toString()
        holder.binding.titleTv.text = post.title
        holder.binding.bodyTv.text = post.body

        holder.binding.rootLayoutCl.setOnClickListener {
            onItemClicked.onItemClickable(post)
        }

    }

    fun setPostItems(postList: List<Post>){
        this.postList.addAll(postList)
        notifyDataSetChanged()
    }
    fun setItemClickedListener(onItemClicked: OnItemClicked){
        this.onItemClicked = onItemClicked
    }

    override fun getItemCount(): Int = postList.size

    class MyViewHolder(var binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClicked {
        fun onItemClickable(post: Post)
    }


}