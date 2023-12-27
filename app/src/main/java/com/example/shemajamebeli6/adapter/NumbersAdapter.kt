package com.example.shemajamebeli6.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli6.R
import com.example.shemajamebeli6.databinding.NumberItemBinding
import com.example.shemajamebeli6.model.NumberModel

class NumbersAdapter(private val click: (NumberModel) -> Unit) :
    ListAdapter<NumberModel, NumbersAdapter.NumberVH>(NumberModelDiffCallback()) {

    inner class NumberVH(private val binding: NumberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: NumberModel) {
            with(binding) {
                when (model.icon) {
                    "delete" -> {
                        tvNumber.visibility = View.GONE
                        ivDeleteOrTouchId.visibility = View.VISIBLE
                        ivDeleteOrTouchId.setImageResource(R.drawable.ic_delete)
                    }
                    "touchId" -> {
                        tvNumber.visibility = View.GONE
                        ivDeleteOrTouchId.visibility = View.VISIBLE
                        ivDeleteOrTouchId.setImageResource(R.drawable.ic_tuch)
                    }
                    else -> {
                        tvNumber.visibility = View.VISIBLE
                        ivDeleteOrTouchId.visibility = View.GONE
                        tvNumber.text = model.icon
                    }
                }
            }
            itemView.setOnClickListener { click(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberVH {
        val binding = NumberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberVH(binding)
    }

    override fun onBindViewHolder(holder: NumberVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class NumberModelDiffCallback : DiffUtil.ItemCallback<NumberModel>() {
        override fun areItemsTheSame(oldItem: NumberModel, newItem: NumberModel): Boolean {
            // Assuming NumberModel has a unique identifier like an ID
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: NumberModel, newItem: NumberModel): Boolean {
            // This is where you define equality for the content of items
            return oldItem == newItem
        }
    }
}