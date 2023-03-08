package com.wasim.smartfoodexchange.models

data class RegisterModel(
    val name: String,
    val email: String,
    val password: String,
    val confimrPassword: String,
    val height: String,
    val weight: String,
    val age: String,
    val gender: String,
)