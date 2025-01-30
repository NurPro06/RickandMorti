package com.example.rickandmorti.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.tooling.data.EmptyGroup.location
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rickandmorti.databinding.FragmentHistoryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryDetailFragment : Fragment() {

    private lateinit var viewBinding: FragmentHistoryDetailBinding
    private val characterViewModel: CharactersViewModel by viewModels()
    private val arguments: HistoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHistoryDetailBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = viewBinding.apply {
        val characterEntity = arguments.character
        val decodedImage = ImageUtils.base64ToBitmap(characterEntity.imageBase64 ?: "")

        name.text = characterEntity.name
        status.text = characterEntity.status
        gender.text = characterEntity.gender
        location.text = characterEntity.location
        species.text = characterEntity.species
        firstSeen.text = characterEntity.firstEpisodeName
        origin.text = characterEntity.origin
        image.load(decodedImage)
    }
}