package com.example.coroutineswitherrorhandling.learn.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coroutineswitherrorhandling.data.model.ApiUser
import com.example.coroutineswitherrorhandling.R
import com.example.coroutineswitherrorhandling.databinding.ItemLayoutBinding

class ApiUserAdapter(private val users: MutableList<ApiUser>) : RecyclerView.Adapter<ApiUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = users[position]
        holder.binding.apply {
            textViewUserName.text = model.name
            textViewUserEmail.text = model.email
            Glide.with(imageViewAvatar.context).load(model.avatar).placeholder(R.drawable.ic_launcher_background).into(imageViewAvatar)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }


    class ViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}