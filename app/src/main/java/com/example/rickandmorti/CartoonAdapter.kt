package com.example.rickandmorti

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorti.data.models.Character

class CartoonAdapter : ListAdapter<Character, CartoonAdapter.CartoonViewHolder>(CartoonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartoonViewHolder {
        val binding = ItemCartoonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartoonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartoonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CartoonViewHolder(private val binding: ItemCartoonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.apply {
                tvName.text = character.name ?: "Unknown Name"
                tvSpecies.text = character.species ?: "Unknown Species"
                ivAvatar.load(character.image)
            }
        }
    }
}


class CartoonDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}
