package com.example.deasa13.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.deasa13.Value
import com.example.deasa13.extensions.isRegistred
import com.example.deasa13.utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth

class RetingViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var dataFirstName: String
    lateinit var dataLastName: String
    lateinit var imgId: String
    var isOk = MutableLiveData<Boolean>()
    init {
        getUserData()
    }

    fun saveData(rating: Int) {
        if (Value.googlePref?.getBoolean("isGoogle", false) == true) {
            val hashMap = hashMapOf<String, Any>(
                "firstName" to FirebaseAuth.getInstance().currentUser?.displayName.toString(),
                "lastName" to "",
                "imgageId" to FirebaseAuth.getInstance().currentUser?.photoUrl.toString(),
                "rating" to rating

            )
            if (isRegistred()) {
                FirebaseUtils().fireStoreDatabase.collection("Rating")
                    .document(FirebaseUtils().uid).set(hashMap).addOnSuccessListener {
                    }
            }

        } else {
            val hashMap = hashMapOf<String, Any>(
                "firstName" to dataFirstName,
                "lastName" to dataLastName,
                "imgageId" to imgId,
                "rating" to rating
            )
            if (isRegistred()) {
                FirebaseUtils().fireStoreDatabase.collection("Rating")
                    .document(FirebaseUtils().uid).set(hashMap).addOnCompleteListener {
                        if (it.isSuccessful) {
                            isOk.value = true
                        }  else {
                            isOk.value = true
                        }
                    }
            }
        }
    }

    fun getUserData() {
        if (isRegistred()) {
            FirebaseUtils().fireStoreDatabase.collection("users")
                .document(FirebaseUtils().uid).get()
                .addOnSuccessListener { querySnapshot ->
                    dataFirstName = querySnapshot.data?.get("firstName").toString()
                    dataLastName = querySnapshot.data?.get("lastName").toString()
                    imgId = querySnapshot.data?.get("imageURL").toString()

                }

        }

    }


}