package com.example.test_neversitup.flow.main.model


import com.google.gson.annotations.SerializedName

data class GetAllTodoResponseModel(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("data")
    val `data`: ArrayList<Data> = ArrayList()
) {
    data class Data(
        @SerializedName("completed")
        val completed: Boolean? = false,
        @SerializedName("createdAt")
        val createdAt: String? = "",
        @SerializedName("description")
        val description: String? = "",
        @SerializedName("_id")
        val id: String? = "",
        @SerializedName("owner")
        val owner: String? = "",
        @SerializedName("updatedAt")
        val updatedAt: String? = "",
        @SerializedName("__v")
        val v: Int? = 0
    )
}