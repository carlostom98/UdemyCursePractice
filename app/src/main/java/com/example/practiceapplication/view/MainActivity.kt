package com.example.practiceapplication.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.practiceapplication.R
import com.example.practiceapplication.databinding.ActivityMainBinding
import com.example.practiceapplication.model.LanguagePreferences
import com.example.practiceapplication.viewModel.LanguageViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    val languageViewModel:LanguageViewModel by viewModels()

    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // add to the toolbar the previus button and burguer menu button
       drawerLayout= binding.drawerMain
        val navController = findNavController(R.id.fragmentContainer)
        NavigationUI.setupActionBarWithNavController(this, navController,drawerLayout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(binding.navigationDraw,navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        //Enable the functions of the nav drawer menu and back arrow
        val navController = findNavController(R.id.fragmentContainer)
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }
}