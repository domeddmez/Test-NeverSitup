package com.example.test_neversitup.flow.login.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("token")
    var token: String? = "",
    @SerializedName("user")
    var user: User? = User()
) {
    data class User(
        @SerializedName("age")
        var age: Int? = 0,
        @SerializedName("createdAt")
        var createdAt: String? = "",
        @SerializedName("email")
        var email: String? = "",
        @SerializedName("_id")
        var id: String? = "",
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("updatedAt")
        var updatedAt: String? = "",
        @SerializedName("__v")
        var v: Int? = 0
    )
}