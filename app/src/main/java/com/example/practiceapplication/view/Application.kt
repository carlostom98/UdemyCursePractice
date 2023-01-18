package com.example.practiceapplication.view
import android.app.Application
import timber.log.Timber

class Application: Application(){
    override fun onCreate() {
        super.onCreate()
        // Manifest Enable and Initialize Timber to use in all your Application
            Timber.plant(Timber.DebugTree())
    }
}