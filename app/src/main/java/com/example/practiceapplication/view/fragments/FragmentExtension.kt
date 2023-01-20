package com.example.practiceapplication.view

import com.example.practiceapplication.R
import com.example.practiceapplication.databinding.FragmentBeginBinding
import com.example.practiceapplication.model.fragmentsText.FragmentOneText
import com.example.practiceapplication.view.fragments.BeginFragment
import java.util.Locale

fun FragmentOneText.setLanguage(activity: MainActivity, binding: FragmentBeginBinding){
    buttonSpeak=activity.context.getText(R.string.speakButton)
    buttonRefresh=activity.context.getText(R.string.refreshButton)
    binding.invalidateAll()
}

fun BeginFragment.setLanguageLocale(lang:String?){
    tts?.apply {
        language=Locale(lang!!)
    }
}
