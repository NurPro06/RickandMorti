package com.example.rickandmorti.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.adapters.OnBoardViewPagerAdapter
import com.example.rickandmorti.R
import com.example.rickandmorti.databinding.FragmentOnboardingBinding
import com.example.rickandmorti.ui.onboarding.OnBoardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private val viewModel: OnBoardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkSharedPreferences()
        initializeViewPager()
        setListeners()
    }

    private fun checkSharedPreferences() {
        if (!viewModel.isFirstTime) {
            findNavController().navigate(R.id.action_onboardingFragment_to_onBoardingPagingFragment)
        }
    }

    private fun initializeViewPager() {
        binding.viewPager.adapter = OnBoardViewPagerAdapter(this)
    }

    private fun setListeners() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 3) {
                    binding.tvSkip.visibility = View.INVISIBLE
                    binding.btnStart.visibility = View.VISIBLE
                    binding.btnStart.setOnClickListener {
                        viewModel.isFirstTime = false
                        findNavController().navigate(R.id.action_onboardingFragment_to_charactersFragment)
                    }
                } else {
                    binding.tvSkip.visibility = View.VISIBLE
                    binding.btnStart.visibility = View.GONE
                    binding.tvSkip.setOnClickListener{
                        binding.viewPager.currentItem = position + 4
                    }
                }
            }
        })
    }
}