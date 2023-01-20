package com.example.practiceapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practiceapplication.databinding.FragmentMaterialDesignBinding
import com.example.practiceapplication.view.MainActivity

class MaterialDesignFragment : Fragment() {

    private var _binding:FragmentMaterialDesignBinding?=null
    private val binding get() = _binding!!

    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.activity = getActivity() as MainActivity
        // Inflate the layout for this fragment
        _binding=FragmentMaterialDesignBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}