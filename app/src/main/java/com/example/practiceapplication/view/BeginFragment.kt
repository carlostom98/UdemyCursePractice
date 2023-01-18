package com.example.practiceapplication.view

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.practiceapplication.R
import com.example.practiceapplication.databinding.FragmentBeginBinding
import com.example.practiceapplication.model.LanguagePreferences
import com.example.practiceapplication.model.ViewReferences
import com.example.practiceapplication.viewModel.LanguageViewModel
import timber.log.Timber
import java.util.*


class BeginFragment : Fragment(), TextToSpeech.OnInitListener, AdapterView.OnItemSelectedListener {
    private var _binding: FragmentBeginBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity: MainActivity
    var tts: TextToSpeech? = null
    private val languageList = arrayOf("en", "es", "fr")
    private val viewReferences = ViewReferences("Hola", "Hola")
    private lateinit var languageSelector:String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        this.activity = getActivity() as MainActivity
        _binding = FragmentBeginBinding.inflate(inflater, container, false)
        binding.buttonRefresh.setOnClickListener {
            it.findNavController()
                .navigate(BeginFragmentDirections.actionBeginFragmentToSecondFragment2("HOLA FRAGMENT"))
        }
        tts = TextToSpeech(this.context, this)
        binding.viewReferences = viewReferences
        binding.spinner.adapter =
            ArrayAdapter(activity.context, android.R.layout.simple_list_item_1, languageList)
        binding.spinner.onItemSelectedListener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.speakButton.setOnClickListener {
            applicationSpeak()
        }

        val lang = LanguagePreferences.getLoginCount()
        val index = languageList.indexOf(lang)
        if (index >= 0) {
            binding.spinner.setSelection(index)
        }
        Timber.i("Language is ${lang}")

        activity.languageViewModel.languageMutableContext.observe(activity){
            activity.context=it
            viewReferences.setLanguage(activity, binding)
            setLanguageLocale(languageSelector)
        }
    }

    private fun applicationSpeak() {
        val message = activity.context.getText(R.string.speech_button)
        tts?.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            binding.textFirstFragment.text = activity.context.getText(R.string.done_text)
            tts?.apply {
                language = Locale("en")
            }
        } else {
            binding.textFirstFragment.text = "Try Again!"
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        activity.languageViewModel.setLanguage(
            parent?.getItemAtPosition(position).toString(),
            activity.context
        )
        languageSelector=parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        //Cerrar el objeto de tts
        tts?.apply {
            stop()
            shutdown()
        }

        //Continuar onDestroy con normalidad
        super.onDestroy()
    }


}