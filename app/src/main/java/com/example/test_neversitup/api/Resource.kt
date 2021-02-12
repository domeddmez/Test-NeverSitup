package com.example.test_neversitup.api

data class Resource<T>(val status: Status, val data: T?, val message: String?, val responseCode: Int = 200) {

    enum class Status{
        SUCCESS, ERROR
    }

    companion object{

        fun <T> success(data: T) : Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(data: T) : Resource<T> {
            return Resource(Status.ERROR, data, null)
        }

        fun <T> error(msg: String, data: T?) : Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> error(code: Int, msg: String, data: T?) : Resource<T> {
            return Resource(Status.ERROR, data, msg, code)
        }
    }
}