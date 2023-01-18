package com.example.practiceapplication.model

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.preference.PreferenceManager
import java.util.*

val PREFERENCE_NAME = "SharedPreferenceExample"
private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

class LanguagePreferences() {
    companion object {
        private var preferences: SharedPreferences ?=null

        fun getLoginCount(): String? {
            return preferences?.getString(SELECTED_LANGUAGE, "en")
        }

        fun setLoginCount(language: String) {
          preferences?.edit()?.also {
                it.putString(SELECTED_LANGUAGE, language)
                it.apply()
            }
        }

        fun setLocale(language: String, context: Context): Context {
            preferences= context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE )
            setLoginCount(language)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                updateResources(context, language)
            } else updateResourcesLegacy(context, language)

        }

        @TargetApi(Build.VERSION_CODES.N)
        private fun updateResources(context: Context, language: String): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)
            val configuration = context.resources.configuration
            configuration.setLocale(locale)
            configuration.setLayoutDirection(locale)
            return context.createConfigurationContext(configuration)
        }

        private fun updateResourcesLegacy(context: Context, language: String): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)
            val resources = context.resources
            val configuration = resources.configuration
            configuration.locale = locale
            configuration.setLayoutDirection(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
            return context
        }
    }
}