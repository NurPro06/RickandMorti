package com.example.rickandmorti.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.rickandmorti.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var viewBinding: FragmentOnBoardPagingBinding

    companion object {
        const val ONBOARDING_INDEX = "onboard_index"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnboardingContent()
    }

    private fun setupOnboardingContent() = viewBinding.apply {
        val (image, title, description) = when (requireArguments().getInt(ONBOARDING_INDEX)) {
            0 -> Triple("https://rickandmortyapi.com/api/character/avatar/1.jpeg", "Rick & Morty", "Explore the multiverse with Rick and Morty.")
            1 -> Triple("https://rickandmortyapi.com/api/character/avatar/18.jpeg", "Characters", "Discover iconic characters from the show.")
            2 -> Triple("https://rickandmortyapi.com/api/character/avatar/14.jpeg", "Locations", "Travel to different dimensions and locations.")
            else -> Triple("https://rickandmortyapi.com/api/character/avatar/11.jpeg", "Episodes", "Dive into episode details and relive the adventures.")
        }

        Glide.with(ivImage).load(image).into(ivImage)
        tvDescriptionTitle.text = title
        tvDescription.text = description
    }
}