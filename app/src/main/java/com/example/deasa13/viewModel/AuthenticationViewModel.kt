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
import com.example.deasa13.extensions.isRegistred
import com.example.deasa13.utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {
    val isRegistreted = MutableLiveData<Boolean>()
    val isLogIn = MutableLiveData<Boolean>()
    val internet = MutableLiveData<Boolean>()


    @RequiresApi(Build.VERSION_CODES.M)
    fun registraton(firstName: String, lastName: String, email: String, password: String) {
        if (checkForInternet(getApplication())) {
            FirebaseUtils().math.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        createUser(firstName, lastName, email, password)
                    }
                }
        } else {
            internet.value = checkForInternet(getApplication())
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun logIn(email: String, password: String) {
        if (checkForInternet(getApplication())) {
            FirebaseUtils().math.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    isLogIn.value = FirebaseAuth.getInstance().currentUser?.isEmailVerified!!
                }
            }
        } else {
            internet.value = checkForInternet(getApplication())
        }
    }

    fun createUser(firstName: String, lastName: String, email: String, password: String) {
        val hasMap = hashMapOf<String, Any>(
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "password" to password,
            "imageURL" to Value.IMAGE_URL
        )

        FirebaseUtils().fireStoreDatabase.collection("users").document(FirebaseUtils().uid)
            .set(hasMap).addOnCompleteListener {
                if (it.isSuccessful) {
                    isRegistreted.value = true
                    setTeamInfo()
                }
            }
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



}
