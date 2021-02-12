package com.example.test_neversitup.flow.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.test_neversitup.api.ApiService
import com.example.test_neversitup.api.Resource
import com.example.test_neversitup.base.BaseViewModel
import com.example.test_neversitup.extention.alertDialog
import com.example.test_neversitup.extention.dismissDialog
import com.example.test_neversitup.extention.savePref
import com.example.test_neversitup.extention.showProgressDialog
import com.example.test_neversitup.flow.login.model.LoginRequestModel
import com.example.test_neversitup.flow.login.model.LoginResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : BaseViewModel() {
    var loginMutableLiveData: MutableLiveData<Resource<LoginResponseModel>> =
        MutableLiveData()

    fun login(req: LoginRequestModel) {
        activity?.showProgressDialog()
        ApiService().createService().callServiceLogin(req).enqueue(object :
            Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                activity?.dismissDialog()
                when {
                    response.isSuccessful -> {
                        serviceSuccess(response, loginMutableLiveData)
                    }
                    else -> {
                        activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                activity?.dismissDialog()
                activity?.alertDialog("ขออภัย", "ระบบขัดข้อง")
            }
        })
    }
}