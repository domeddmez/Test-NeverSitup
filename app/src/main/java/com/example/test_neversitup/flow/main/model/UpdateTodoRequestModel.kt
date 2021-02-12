package com.example.test_neversitup.flow.main.model


import com.google.gson.annotations.SerializedName

data class UpdateTodoRequestModel(
    @SerializedName("completed")
    val completed: Boolean? = false,
    @SerializedName("description")
    val description: String? = ""
)