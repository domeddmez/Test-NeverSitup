package com.example.test_neversitup.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiService {
    private lateinit var service: Service

    fun createService(): Service {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl("https://api-nodejs-todolist.herokuapp.com/")
            .addConverterFactory(addConverter())
            .client(client)
            .build()

        service = retrofit.create(Service::class.java)
        return service
    }

    private fun addConverter(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }
}