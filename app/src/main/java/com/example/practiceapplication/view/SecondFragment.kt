package com.example.practiceapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.practiceapplication.databinding.FragmentSecondBinding
import com.example.practiceapplication.model.fragmentsText.FragmentTwoText
import com.example.practiceapplication.viewModel.RequestDataViewModel
import kotlinx.coroutines.*

class SecondFragment() : Fragment(){

    private var _binding:FragmentSecondBinding?=null
    private val binding get() = _binding!!
    private lateinit var activity: MainActivity
    private val args:SecondFragmentArgs by navArgs()
    var fragmentTwoText=FragmentTwoText()
    private val requestDataViewModel:RequestDataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.activity = getActivity() as MainActivity
        _binding= FragmentSecondBinding.inflate(inflater,container, false)
        // Finalizar todas las corrutinas así una falle
        binding.textSecondFragment=fragmentTwoText
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Coroutines practice
        // Global Scope crea hilo secundario
        //LifecycleScope utiliza el mismo hilo
        lifecycleScope.launch {
            val numero1 = async (Dispatchers.IO) {getNumero1()}
            val numero2 = async (Dispatchers.IO) {getNumero2()}
            //Las funciones de suspención bloquean la corrutina hasta tener un resultado
            //Await bloquea la corrutina hasta tener un resultado de ambos números
            Toast.makeText(this@SecondFragment.context, "El número1 es ${numero1.await()} y el numero 2 es ${numero2.await()}" , Toast.LENGTH_SHORT).show()
        }

        binding.buttonRequest.setOnClickListener {
            requestDataViewModel.requestChange()
        }

        requestDataViewModel.requestState.observe(activity){
            fragmentTwoText.textOne=it
            fragmentTwoText.textTwo=it
            binding.invalidateAll()
        }
    }
     fun getNumero1():Float{
        Thread.sleep(2000)
        return 10000F
    }
    fun getNumero2():Float{
        Thread.sleep(3000)
        return 20000F
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}