package com.example.rickandmorti

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.rickandmorti.databinding.ActivityMain2Binding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private val viewModel: OnBoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragments, CharactersFragment())
//            .commit()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragments) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavView.setupWithNavController(navController)
        binding.bottomNavView.visibility = if (viewModel.isFirstTime) View.GONE else View.VISIBLE
    }
}