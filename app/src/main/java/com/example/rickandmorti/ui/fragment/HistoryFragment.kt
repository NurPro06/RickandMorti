package com.example.rickandmorti.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorti.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(), OnItemClick {

    private lateinit var viewBinding: FragmentHistoryBinding
    private val characterViewModel: CharactersViewModel by viewModels()
    private val historyAdapter by lazy { HistoryAdapter(characterViewModel, this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHistoryBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() = viewBinding.recyclerView.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = historyAdapter
    }

    private fun observeData() {
        characterViewModel.getViewedCharacters().observe(viewLifecycleOwner) { historyList ->
            historyAdapter.submitList(historyList)
        }
    }

    override fun onItemClick(character: CharacterEntity) {
        val action = HistoryFragmentDirections
            .actionViewedCharactersFragmentToHistoryDetailFragment(character)
        findNavController().navigate(action)
    }
}
