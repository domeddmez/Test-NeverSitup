package com.example.test_neversitup.flow.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.test_neversitup.R
import com.example.test_neversitup.base.BaseActivity
import com.example.test_neversitup.databinding.ActivityLoginBinding
import com.example.test_neversitup.extention.alertDialog
import com.example.test_neversitup.extention.getPreferBoolean
import com.example.test_neversitup.extention.savePref
import com.example.test_neversitup.extention.validateEmail
import com.example.test_neversitup.flow.login.model.LoginRequestModel
import com.example.test_neversitup.flow.login.viewmodel.LoginViewModel
import com.example.test_neversitup.flow.main.MainActivity
import com.example.test_neversitup.flow.register.RegisterActivity
import com.example.test_neversitup.flow.register.model.RegisterRequestModel
import com.example.test_neversitup.room.LoginModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActivity :
    BaseActivity<ActivityLoginBinding, LoginViewModel>(LoginViewModel::class.java),
    View.OnClickListener {

    override var layoutResource = R.layout.activity_login

    override fun initInstance() {
        super.initInstance()
        binding.btLogin.setOnClickListener(this)
        binding.btRegister.setOnClickListener(this)

        if (getPreferBoolean("login")) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun validateInputData() {
        binding.apply {
            when {
                edEmail.text.toString().validateEmail() -> {
                    alertDialog("ขออภัย", "กรุณากรอกอีเมลที่ถูกต้อง")
                    return
                }
                edPassword.text.toString().isNullOrEmpty() -> {
                    alertDialog("ขออภัย", "กรุณากรอกรหัส")
                    return
                }
                else -> {
                    viewModel.login(
                        LoginRequestModel(
                            email = edEmail.text.toString(),
                            password = edPassword.text.toString()
                        )
                    )
                    return
                }
            }
        }
    }

    override fun subscribeLiveData() {
        super.subscribeLiveData()
        viewModel.loginMutableLiveData.observe(this, {
            val loginModel = LoginModel()
            loginModel.token = it.data?.token
            loginModel.age = it.data?.user?.age
            loginModel.createdAt = it.data?.user?.createdAt
            loginModel.email = it.data?.user?.email
            loginModel.ids = it.data?.user?.id
            loginModel.name = it.data?.user?.name
            loginModel.updatedAt = it.data?.user?.updatedAt
            loginModel.v = it.data?.user?.v
            Flowable.fromCallable { appDatabase.customerDao().insertCustomerData(loginModel) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                  //  savePref("token", it.data?.token.toString())
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btRegister -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
            R.id.btLogin -> {
                validateInputData()
            }
        }
    }
}