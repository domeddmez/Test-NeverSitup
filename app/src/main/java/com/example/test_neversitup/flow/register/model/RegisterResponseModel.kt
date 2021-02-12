package com.example.test_neversitup.flow.register.model


import com.google.gson.annotations.SerializedName

data class RegisterResponseModel(
    @SerializedName("token")
    val token: String? = "",
    @SerializedName("user")
    val user: User? = User()
) {
    data class User(
        @SerializedName("age")
        val age: Int? = 0,
        @SerializedName("createdAt")
        val createdAt: String? = "",
        @SerializedName("email")
        val email: String? = "",
        @SerializedName("_id")
        val id: String? = "",
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("updatedAt")
        val updatedAt: String? = "",
        @SerializedName("__v")
        val v: Int? = 0
    )
}