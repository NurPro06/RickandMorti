package com.example.rickandmorti.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rickandmorti.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private lateinit var viewBinding: FragmentCharacterDetailBinding
    private val characterViewModel: CharactersViewModel by viewModels()
    private val arguments: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = viewBinding.apply {
        val characterData = arguments.character
        name.text = characterData.name
        status.append(characterData.status)
        species.append(characterData.species)
        gender.append(characterData.gender)
        origin.append(characterData.origin?.name ?: "Unknown")
        location.append(characterData.location?.name ?: "Unknown")
        image.load(characterData.image)

        characterData.episode?.firstOrNull()?.let { firstEpisode ->
            characterViewModel.getEpisodeNameForCharacter(firstEpisode) { episodeTitle ->
                firstSeen.append(episodeTitle)
            }
        }

        characterViewModel.saveViewedCharacter(characterData)
    }
}
