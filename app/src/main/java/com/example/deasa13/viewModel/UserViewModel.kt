package com.example.deasa13.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deasa13.model.UserModel
import com.example.deasa13.utils.FirebaseUtils

class UserViewModel : ViewModel() {
    private val _user = MutableLiveData<UserModel>()
    var user: LiveData<UserModel> = _user


    fun getUserData() {
        FirebaseUtils().fireStoreDatabase.collection("users").document(FirebaseUtils().uid)
            .get().addOnSuccessListener {
                _user.value = UserModel(
                    it.data!!["firstName"].toString(),
                    it.data!!["lastName"].toString(),
                    it.data!!["email"].toString(),
                    it.data!!["password"].toString(),
                    it.data!!["imageURL"].toString()
                )
            }
    }

}