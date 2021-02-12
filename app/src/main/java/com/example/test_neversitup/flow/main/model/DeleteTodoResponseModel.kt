package com.example.test_neversitup.flow.main.model


import com.google.gson.annotations.SerializedName

data class DeleteTodoResponseModel(
    @SerializedName("data")
    val `data`: Data? = Data(),
    @SerializedName("success")
    val success: Boolean? = false
) {
    class Data(
    )
}