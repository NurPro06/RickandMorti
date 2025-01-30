package com.example.rickandmorti.ui.onboarding

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val prefs: SharedPreferences
) : ViewModel() {

    var isFirstLaunch: Boolean
        get() = prefs.getBoolean("first_time_user", true)
        set(value) = prefs.edit().putBoolean("first_time_user", value).apply()
}