package com.example.test_neversitup.flow.login.model


import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("password")
    val password: String? = ""
)