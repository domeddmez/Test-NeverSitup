package com.example.test_neversitup.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "customer")
data class LoginModel(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("age")
    var age: Int? = 0,
    @SerializedName("createdAt")
    var createdAt: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("_id")
    var ids: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("updatedAt")
    var updatedAt: String? = "",
    @SerializedName("__v")
    var v: Int? = 0
) {
}