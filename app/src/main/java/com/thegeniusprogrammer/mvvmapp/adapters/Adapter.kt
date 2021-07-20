package com.thegeniusprogrammer.mvvmapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thegeniusprogrammer.mvvmapp.R
import com.thegeniusprogrammer.mvvmapp.databinding.ItemsRowBinding
import com.thegeniusprogrammer.mvvmapp.model.Posts

class Adapter : RecyclerView.Adapter<Adapter.PostViewHolder>() {

    private val diffUtils = object : DiffUtil.ItemCallback<Posts>() {
        override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem.body == newItem.body
        }

        override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtils)


    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemsRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.textView.text = differ.currentList[position].body
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}