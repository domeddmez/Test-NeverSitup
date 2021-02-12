package com.example.test_neversitup.flow.register.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.test_neversitup.api.ApiService
import com.example.test_neversitup.api.Resource
import com.example.test_neversitup.base.BaseViewModel
import com.example.test_neversitup.extention.alertDialog
import com.example.test_neversitup.extention.dismissDialog
import com.example.test_neversitup.extention.showProgressDialog
import com.example.test_neversitup.flow.register.model.RegisterRequestModel
import com.example.test_neversitup.flow.register.model.RegisterResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : BaseViewModel() {
    var registerMutableLiveData: MutableLiveData<Resource<RegisterResponseModel>> =
        MutableLiveData()

    fun register(req: RegisterRequestModel) {
        activity?.showProgressDialog()
        ApiService().createService().callServiceRegister(req).enqueue(object :
            Callback<RegisterResponseModel> {
            override fun onResponse(
                call: Call<RegisterResponseModel>,
                response: Response<RegisterResponseModel>
            ) {
                activity?.dismissDialog()
                when {
                    response.isSuccessful -> {
                        serviceSuccess(response, registerMutableLiveData)
                    }
                    else -> {
                        activity?.alertDialog("ขออภัย","ระบบขัดข้อง")
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
                activity?.dismissDialog()
                activity?.alertDialog("ขออภัย","ระบบขัดข้อง")
            }
        })
    }
}