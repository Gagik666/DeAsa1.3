package com.example.deasa13.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseUtils {
    val math = FirebaseAuth.getInstance()
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    val isVerifi = FirebaseAuth.getInstance().currentUser
    val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
    val mStaorageRef = FirebaseStorage.getInstance().reference
}