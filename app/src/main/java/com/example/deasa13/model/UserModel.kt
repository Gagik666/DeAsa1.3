package com.example.deasa13.model

data class UserModel(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    val imgURL: String,
    var rating: Int = 0,
)