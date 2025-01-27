package com.example.rickandmorti.ui.onboarding

import android.content.SharedPreferences
import jakarta.inject.Inject

class PreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val PREFS_NAME = "app_preferences"
        private const val KEY_ONBOARDING_SHOWN = "onboarding_shown"
    }

    fun setOnboardingShown(shown: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_ONBOARDING_SHOWN, shown).apply()
    }

    fun isOnboardingShown(): Boolean {
        return sharedPreferences.getBoolean(KEY_ONBOARDING_SHOWN, false)
    }
}