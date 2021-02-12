package com.example.test_neversitup.flow.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.test_neversitup.api.ApiService
import com.example.test_neversitup.api.Resource
import com.example.test_neversitup.base.BaseViewModel
import com.example.test_neversitup.extention.alertDialog
import com.example.test_neversitup.extention.dismissDialog
import com.example.test_neversitup.extention.showProgressDialog
import com.example.test_neversitup.flow.main.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : BaseViewModel() {
    var logoutMutableLiveData: MutableLiveData<Resource<LogOutResponseModel>> =
        MutableLiveData()
    var getAllTodoMutableLiveData: MutableLiveData<Resource<GetAllTodoResponseModel>> =
        MutableLiveData()
    var getTodoMutableLiveData: MutableLiveData<Resource<GetTodoResponseModel>> =
        MutableLiveData()
    var createTodoMutableLiveData: MutableLiveData<Resource<CreateTodoResponseModel>> =
        MutableLiveData()
    var updateTodoMutableLiveData: MutableLiveData<Resource<UpdateTodoResponseModel>> =
        MutableLiveData()
    var deleteTodoMutableLiveData: MutableLiveData<Resource<DeleteTodoResponseModel>> =
        MutableLiveData()

    fun logout(token: String?) {
        activity?.showProgressDialog()
        ApiService().createService().callServiceLogout(token).enqueue(object :
            Callback<LogOutResponseModel> {
            override fun onResponse(
                call: Call<LogOutResponseModel>,
                response: Response<LogOutResponseModel>
            ) {
                activity?.dismissDialog()
                when {
                    response.isSuccessful -> {
                        serviceSuccess(response, logoutMutableLiveData)
                    }
                    else -> {
                        activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
                    }
                }
            }

            override fun onFailure(call: Call<LogOutResponseModel>, t: Throwable) {
                activity?.dismissDialog()
                activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
            }
        })
    }

    fun getAllTodo(token: String?) {
        activity?.showProgressDialog()
        ApiService().createService().callServiceGetAllTodo(token).enqueue(object :
            Callback<GetAllTodoResponseModel> {
            override fun onResponse(
                call: Call<GetAllTodoResponseModel>,
                responseAll: Response<GetAllTodoResponseModel>
            ) {
                activity?.dismissDialog()
                when {
                    responseAll.isSuccessful -> {
                        println("dome ${responseAll.body().toString()}")
                        serviceSuccess(responseAll, getAllTodoMutableLiveData)
                    }
                    else -> {
                        println("dome ${responseAll.body().toString()}")
                        activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
                    }
                }
            }

            override fun onFailure(call: Call<GetAllTodoResponseModel>, t: Throwable) {
                activity?.dismissDialog()
                activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
            }
        })
    }

    fun getTodo(token: String?, id: String?) {
        activity?.showProgressDialog()
        ApiService().createService().callServiceGetTodo(token, id).enqueue(object :
            Callback<GetTodoResponseModel> {
            override fun onResponse(
                call: Call<GetTodoResponseModel>,
                responseAll: Response<GetTodoResponseModel>
            ) {
                activity?.dismissDialog()
                when {
                    responseAll.isSuccessful -> {
                        println("dome ${responseAll.body().toString()}")
                        serviceSuccess(responseAll, getTodoMutableLiveData)
                    }
                    else -> {
                        println("dome ${responseAll.body().toString()}")
                        activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
                    }
                }
            }

            override fun onFailure(call: Call<GetTodoResponseModel>, t: Throwable) {
                activity?.dismissDialog()
                activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
            }
        })
    }

    fun createTodo(token: String?, req: CreateTodoRequestModel) {
        activity?.showProgressDialog()
        ApiService().createService().callServiceCreateTodo(token, req).enqueue(object :
            Callback<CreateTodoResponseModel> {
            override fun onResponse(
                call: Call<CreateTodoResponseModel>,
                response: Response<CreateTodoResponseModel>
            ) {
                activity?.dismissDialog()
                when {
                    response.isSuccessful -> {
                        serviceSuccess(response, createTodoMutableLiveData)
                    }
                    else -> {
                        activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
                    }
                }
            }

            override fun onFailure(call: Call<CreateTodoResponseModel>, t: Throwable) {
                activity?.dismissDialog()
                activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
            }
        })
    }

    fun updateTodo(token: String?, req: UpdateTodoRequestModel) {
        activity?.showProgressDialog()
        ApiService().createService().callServiceUpdateTodo(token, req).enqueue(object :
            Callback<UpdateTodoResponseModel> {
            override fun onResponse(
                call: Call<UpdateTodoResponseModel>,
                response: Response<UpdateTodoResponseModel>
            ) {
                activity?.dismissDialog()
                when {
                    response.isSuccessful -> {
                        serviceSuccess(response, updateTodoMutableLiveData)
                    }
                    else -> {
                        activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
                    }
                }
            }

            override fun onFailure(call: Call<UpdateTodoResponseModel>, t: Throwable) {
                activity?.dismissDialog()
                activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
            }
        })
    }

    fun deleteTodo(token: String?, id: String?) {
        activity?.showProgressDialog()
        ApiService().createService().callServiceDeleteTodo(token, id).enqueue(object :
            Callback<DeleteTodoResponseModel> {
            override fun onResponse(
                call: Call<DeleteTodoResponseModel>,
                response: Response<DeleteTodoResponseModel>
            ) {
                activity?.dismissDialog()
                when {
                    response.isSuccessful -> {
                        serviceSuccess(response, deleteTodoMutableLiveData)
                    }
                    else -> {
                        activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
                    }
                }
            }

            override fun onFailure(call: Call<DeleteTodoResponseModel>, t: Throwable) {
                activity?.dismissDialog()
                activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
            }
        })
    }
}