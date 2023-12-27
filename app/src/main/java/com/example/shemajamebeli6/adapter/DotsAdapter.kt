package com.example.shemajamebeli6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli6.R
import com.example.shemajamebeli6.databinding.DotItemBinding
import com.example.shemajamebeli6.model.DotModel

class DotsAdapter : ListAdapter<DotModel, DotsAdapter.DotVH>(DotModelDiffCallback()) {

    inner class DotVH(private val binding: DotItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: DotModel) {
            with(binding) {
                if (model.color == "gray") {
                    root.setImageResource(R.drawable.ic_dot_gray)
                } else {
                    root.setImageResource(R.drawable.ic_dot_green)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotVH {
        val binding = DotItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DotVH(binding)
    }

    override fun onBindViewHolder(holder: DotVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DotModelDiffCallback : DiffUtil.ItemCallback<DotModel>() {
        override fun areItemsTheSame(oldItem: DotModel, newItem: DotModel): Boolean {
            // Assuming DotModel has a unique identifier like an ID
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DotModel, newItem: DotModel): Boolean {
            // This is where you define equality for the content of items
            return oldItem == newItem
        }
    }
}