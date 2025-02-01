package com.example.rickandmorti.ui.onboarding

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(private val sharedPreferences: SharedPreferences): ViewModel() {
    var isFirstTime: Boolean
        get() = sharedPreferences.getBoolean("onboarding_first_time", true)
        set(value) = sharedPreferences.edit().putBoolean("onboarding_first_time", value).apply()
}
