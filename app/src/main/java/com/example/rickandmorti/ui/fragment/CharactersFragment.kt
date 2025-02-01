package com.example.rickandmorti.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorti.OnItemClick
import com.example.rickandmorti.databinding.FragmentCharactersBinding
import com.example.rickandmorti.ui.adapter.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(), OnItemClick {

    private lateinit var viewBinding: FragmentCharactersBinding
    private val characterViewModel: CharactersViewModel by viewModels()
    private val characterAdapter by lazy { CharactersAdapter(characterViewModel, this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCharactersBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() = viewBinding.recyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = characterAdapter
    }

    private fun observeData() {
        characterViewModel.getCharacters()
        characterViewModel.charactersData.observe(viewLifecycleOwner) { list ->
            characterAdapter.submitList(list)
        }
    }

    override fun onItemClick(character: Character) {
        val navigateAction = CharactersFragmentDirections
            .actionCharactersFragmentToCharacterDetailFragment(character)
        findNavController().navigate(navigateAction)
    }
}