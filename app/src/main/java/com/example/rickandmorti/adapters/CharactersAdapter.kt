    package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.adapters

    import android.view.LayoutInflater
    import android.view.ViewGroup

    class CharactersAdapter(private val viewModel: CharactersViewMode, private val onClick: OnItemClick) : PagingDataAdapter<Character, CharactersAdapter.ViewHolder>(DiffCallback()) {

        class ViewHolder(private val binding: CharacterItemBinding, private val viewModel: CharactersViewModel) : RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Character?) {
                binding.apply {
                    name.text = item?.name
                    status.text = item?.status
                    species.text = item?.species
                    location.text = item?.location?.name ?: "???"
                    viewModel.getEpisodeNameForCharacter(item?.episode?.firstOrNull() ?: "") { episodeName ->
                        firstSeen.text = episodeName
                    }

                    val characterStatus = when (item?.status) {
                        "Alive" -> CharacterStatus.ALIVE
                        "Dead" -> CharacterStatus.DEAD
                        else -> CharacterStatus.UNKNOWN
                    }
                    statusIcon.imageTintList = statusIcon.context.getColorStateList(characterStatus.colorResId)
                    imageView.load(item?.image)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                viewModel
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position))
            holder.itemView.setOnClickListener {
                onClick.onItemClick(getItem(position)!!)
            }
        }

        class DiffCallback : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }