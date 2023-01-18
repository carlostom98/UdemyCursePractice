package com.example.practiceapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.practiceapplication.R
import com.example.practiceapplication.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding:FragmentSecondBinding?=null
    private val binding get() = _binding!!
    private lateinit var activity: MainActivity
    private val args:SecondFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.activity = getActivity() as MainActivity
        _binding= FragmentSecondBinding.inflate(inflater,container, false)
        Toast.makeText(this.context, args.hiFragment, Toast.LENGTH_SHORT).show()
        return binding.root
    }
}