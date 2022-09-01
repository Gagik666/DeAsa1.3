package com.example.deasa13.viewModel

import android.app.Activity
import android.app.Application
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.deasa12.database.database.SingerInfo
import com.example.deasa13.DataList
import com.example.deasa13.model.TeamModel
import com.example.deasa13.utils.FirebaseUtils
import com.example.studentapp.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartViewModel(application: Application) : AndroidViewModel(application) {

    init {
        getSingers()
        updateLIsts()
    }

    private fun getSingers() {
        FirebaseUtils().fireStoreDatabase.collection("Singers")
            .document("8kQ9ldNOpTUc4zrwKdPr").get()
            .addOnSuccessListener { Task ->
                val db = UserDatabase.getDatabase(getApplication())
                CoroutineScope(Dispatchers.IO).launch {
                    Task.data?.forEach {
                        if (db.userDao().isNotExists(it.value.toString())) {
                            db.userDao().insertData(
                                SingerInfo(it.value.toString())
                            )
                        }
                    }
                }

            }
    }

    fun setSingers() {
        val db = UserDatabase.getDatabase(getApplication())
        CoroutineScope(Dispatchers.IO).launch {
            db.userDao().getAll().forEach {
                DataList.listSingeer.add(it.name)
            }
        }
    }

    fun updateLIsts() {
        DataList.usersList.clear()
    }

}
