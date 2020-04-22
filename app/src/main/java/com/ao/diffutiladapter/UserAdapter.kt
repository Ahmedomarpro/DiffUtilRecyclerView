package com.ao.diffutiladapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ao.diffutiladapter.databinding.ItemUserBinding

class UserAdapter : ListAdapter<User, UserAdapter.VH>(object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.uid == newItem.uid


    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {

        return oldItem == newItem

    }
}) {


    /*
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH (
           ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)

       )

    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val View = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(View)
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    class VH(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.emailTextView.text = item.email
            binding.nameTextView.text = item.name

        }

    }


}