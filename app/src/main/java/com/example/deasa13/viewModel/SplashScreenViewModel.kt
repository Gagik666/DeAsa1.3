package com.example.deasa13.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class SplashScreenViewModel : ViewModel() {
    val size = MutableLiveData<Int>()
    val splashEnd = MutableLiveData<Boolean>()

    init {
        CoroutineScope(Dispatchers.Main).launch {
            changeSizeText()
        }

    }

    private suspend fun changeSizeText() {
        var i = 15
        while (i <= 48) {
            delay(70)
            withContext(Dispatchers.Main) {
                size.value = i
            }
            if (i == 48) {
                splashEnd.value = true
            }
            i++
        }
    }
}