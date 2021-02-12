package com.example.test_neversitup.flow.main.model


import com.google.gson.annotations.SerializedName

data class LogOutResponseModel(
    @SerializedName("success")
    val success: Boolean? = false
)