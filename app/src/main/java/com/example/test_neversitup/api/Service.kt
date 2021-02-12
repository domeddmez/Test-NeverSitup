package com.example.test_neversitup.api

import com.example.test_neversitup.flow.login.model.LoginRequestModel
import com.example.test_neversitup.flow.login.model.LoginResponseModel
import com.example.test_neversitup.flow.main.model.*
import com.example.test_neversitup.flow.register.model.RegisterRequestModel
import com.example.test_neversitup.flow.register.model.RegisterResponseModel
import retrofit2.Call
import retrofit2.http.*

interface Service {
    @POST("user/register")
    fun callServiceRegister(@Body req: RegisterRequestModel): Call<RegisterResponseModel>

    @POST("user/login")
    fun callServiceLogin(@Body req: LoginRequestModel): Call<LoginResponseModel>

    @POST("user/logout")
    fun callServiceLogout(@Header("Authorization") token: String?): Call<LogOutResponseModel>

    @POST("task")
    fun callServiceCreateTodo(
        @Header("Authorization") token: String?,
        @Body req: CreateTodoRequestModel
    ): Call<CreateTodoResponseModel>

    @GET("task")
    fun callServiceGetAllTodo(
        @Header("Authorization") token: String?
    ): Call<GetAllTodoResponseModel>

    @GET("task/{id}")
    fun callServiceGetTodo(
        @Header("Authorization") token: String?,
        @Path("id") id: String?
    ): Call<GetTodoResponseModel>

    @POST("task")
    fun callServiceUpdateTodo(
        @Header("Authorization") token: String?,
        @Body req: UpdateTodoRequestModel
    ): Call<UpdateTodoResponseModel>

    @DELETE("task/{id}")
    fun callServiceDeleteTodo(
        @Header("Authorization") token: String?,
        @Path("id") id: String?
    ): Call<DeleteTodoResponseModel>
}