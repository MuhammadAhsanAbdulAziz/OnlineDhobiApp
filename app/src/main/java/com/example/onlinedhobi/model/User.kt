package com.example.onlinedhobi.model

data class User(
    val CNIC: String,
    val Email: String,
    val FName: String,
    val LName: String,
    val Password: String,
    val Phone: String,
    val RoleId: Int,
    val UserId: Int? = null,
    val CreatedAt: String? = null
)