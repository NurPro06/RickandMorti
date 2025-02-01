package com.example.rickandmorti.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.rickandmorti.OnItemClick
import com.example.rickandmorti.ui.onboarding.CharactersViewModel

class CharactersAdapter(
    private val viewModel: CharactersViewModel,
    private val onClick: OnItemClick
) : ListAdapter<Character, CharactersAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            with(binding) {
                name.text = character.name
                status.text = character.status ?: "Unknown"
                species.text = character.species ?: "Unknown"
                location.text = character.location?.name ?: "???"

                viewModel.getEpisodeNameForCharacter(character.episode?.firstOrNull() ?: "") { episodeName ->
                    firstSeen.text = episodeName
                }

                val characterStatus = when (character.status) {
                    "Alive" -> CharacterStatus.ALIVE
                    "Dead" -> CharacterStatus.DEAD
                    else -> CharacterStatus.UNKNOWN
                }
                statusIcon.imageTintList = statusIcon.context.getColorStateList(characterStatus.colorResId)
                imageView.load(character.image)

                root.setOnClickListener { onClick.onItemClick(character) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem
    }
}