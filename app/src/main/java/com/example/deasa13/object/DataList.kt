package com.example.deasa13

import com.example.deasa13.model.TeamModel
import com.example.deasa13.model.UsersModel

object DataList {
    val teamList = mutableListOf(
       TeamModel("Team", 0),
       TeamModel("Team", 0),
    )
    val oldTeamList = mutableListOf<String>()
    val usersList = mutableListOf<UsersModel>()

    var listSingeer = mutableListOf<String>()
    val tempList = mutableListOf<String>()

}