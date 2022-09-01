package com.example.deasa13.viewModel

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.deasa13.DataList
import com.example.deasa13.Value
import com.example.deasa13.extensions.checkForInternet
import com.example.deasa13.extensions.dialog
import com.example.deasa13.extensions.isRegistred
import com.example.deasa13.model.TeamModel
import com.example.deasa13.utils.FirebaseUtils

class PointViewModel(application: Application) : AndroidViewModel(application) {
    val gameOver = MutableLiveData<Boolean>()
    val teamList = MutableLiveData<List<TeamModel>>()
    val oldTeamList = MutableLiveData<List<String>>()
    val checkInternet = MutableLiveData<Boolean>()

    init {
        teamInfo()
        getTeamInfo()
    }

    private fun teamInfo() {
        if (Value.queue == (DataList.teamList.size * 2) - 1) {
            gameOver.value = true
            Value.teamIndex = 0
            Value.queue = 0
        } else {
            gameOver.value = false
        }
        teamList.value = DataList.teamList
    }

    private fun setTeamInfo() {

        val hashMap = HashMap<String, Any>()
        for (i in 0 until DataList.teamList.size) {
            hashMap.put("${i}", "${DataList.teamList[i].team} --> ${DataList.teamList[i].point}")

        }

        if (isRegistred()) {
            FirebaseUtils().fireStoreDatabase.collection("Teams")
                .document(FirebaseUtils().uid).set(hashMap).addOnSuccessListener {
                    Toast.makeText(getApplication(), "ok", Toast.LENGTH_SHORT).show()
                }
        }
    }

    fun getTeamInfo() {
        if (isRegistred()) {
            FirebaseUtils().fireStoreDatabase.collection("Teams")
                .document(FirebaseUtils().uid).get().addOnSuccessListener {
                    for (i in 0 until it.data?.size!!) {
                        DataList.oldTeamList.add(it.data?.get("$i").toString())
                    }
                }
            oldTeamList.value = DataList.oldTeamList
        }
    }

    fun playAgain() {
        for (i in 0 until DataList.teamList.size) {
            DataList.teamList[i].point = 0
        }
    }

    fun updateTeam() {
        Value.teamIndex++
        Value.queue++
        if (Value.queue == DataList.teamList.size) {
            Value.teamIndex = 0
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun refreshData() {
        if (checkForInternet(getApplication())) {
            Toast.makeText(getApplication(), "internet OK", Toast.LENGTH_SHORT).show()
            setTeamInfo()
        } else {
            checkInternet.value = false
        }
    }


}