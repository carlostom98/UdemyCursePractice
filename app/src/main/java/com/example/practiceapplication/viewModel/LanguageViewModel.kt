package com.example.practiceapplication.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practiceapplication.model.LanguagePreferences

class LanguageViewModel : ViewModel() {

    val languageMutableContext = MutableLiveData<Context>()

    fun setLanguage(language: String, context: Context) {
        val contextLanguage = LanguagePreferences.setLocale(language, context)
        languageMutableContext.postValue(contextLanguage)
    }
}