package com.example.deasa13.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deasa13.DataList
import com.example.deasa13.R
import com.example.deasa13.Value
import com.example.deasa13.model.TeamModel

class SelsectViewModel : ViewModel() {
    val tempList = MutableLiveData<List<TeamModel>>()

    init {
        getTeamList()
    }

    private fun getTeamList() {
        tempList.value = DataList.teamList
    }

    fun addTeam() {
        DataList.teamList.add(TeamModel("Team", 0))
    }

    fun shuffle() {
        DataList.listSingeer.shuffle()
    }


}