package com.example.practiceapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RequestDataViewModel:ViewModel() {
    val requestState = MutableLiveData<String?>()

    fun requestChange(){
        viewModelScope.launch {
           requestState.postValue(withContext(Dispatchers.IO){
                makeRequest()
            })
        }
    }

    private fun makeRequest():String{
        Thread.sleep(3000)
        return "Done Request"
    }
}