package com.example.test_neversitup.flow.register.model


import com.google.gson.annotations.SerializedName

data class RegisterRequestModel(
    @SerializedName("age")
    val age: Int? = 0,
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("password")
    val password: String? = ""
)