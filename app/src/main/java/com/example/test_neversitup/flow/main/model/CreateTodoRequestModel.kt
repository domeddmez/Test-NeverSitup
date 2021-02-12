package com.example.test_neversitup.flow.main.model


import com.google.gson.annotations.SerializedName

data class CreateTodoRequestModel(
    @SerializedName("description")
    val description: String? = ""
)