package com.example.deasa13.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.deasa13.DataList
import com.example.deasa13.model.UsersModel
import com.example.deasa13.utils.FirebaseUtils

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    var usersList = MutableLiveData<List<UsersModel>>()

    init {
        getUsers()
    }

    fun getUsers() {
        FirebaseUtils().fireStoreDatabase.collection("Rating").get()
            .addOnSuccessListener { Task ->
                Task.forEach {
                    DataList.usersList.add(
                        UsersModel(
                            it.data["firstName"].toString(),
                            it.data["lastName"].toString(),
                            it.data["imgageId"].toString(),
                            it.data["rating"].toString().toInt()
                        )
                    )

                }
                usersList.value = DataList.usersList.toMutableSet().toList()
            }

    }
}