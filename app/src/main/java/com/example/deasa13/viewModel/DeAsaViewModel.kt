package com.example.deasa13.viewModel


import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.deasa13.DataList
import com.example.deasa13.Value
import com.example.deasa13.Value.p
import com.example.deasa13.Value.timer
import kotlinx.coroutines.*

class DeAsaViewModel(application: Application) : AndroidViewModel(application) {
    val second = MutableLiveData<Int>()
    val teamName = MutableLiveData<String>()
    val tempList = MutableLiveData<List<String>>()
    val updateTempList = MutableLiveData<Boolean>()
    val tempPoint = MutableLiveData<Int>()

    init {
        CoroutineScope(Dispatchers.Main).launch {
            timer()
        }
        getTeamName()
        tempPoint.value = 0
    }

    private fun getTeamName() {
        teamName.value = DataList.teamList[Value.teamIndex].team
    }

    private suspend fun timer() {
        for (i in timer downTo 0) {
            delay(1000)
            withContext(Dispatchers.Main) {
                second.value = i
            }
            if (i == 0) {
                DataList.tempList.clear()
                tempPoint.value = 0
                p = 0
            }
        }
    }

    fun getSingerTempList() {
        Value.start += 5
        Value.end += 5
        for (i in Value.start..Value.end) {
            DataList.tempList.add(DataList.listSingeer[i])
            Value.step = i
        }
        tempList.value = DataList.tempList
    }

    fun updatePoint(click: Boolean) {
        if (click) {
            tempPoint.value = tempPoint.value?.plus(1)
            p++
            DataList.teamList[Value.teamIndex].point++
        } else {
            tempPoint.value = tempPoint.value?.minus(1)
            p--
            DataList.teamList[Value.teamIndex].point--
        }
        if (p == 5) {
            updateTempList.value = true
            p = 0
            DataList.tempList.clear()
            if (DataList.tempList.size == 0) {
                getSingerTempList()
            }
        }
    }

}