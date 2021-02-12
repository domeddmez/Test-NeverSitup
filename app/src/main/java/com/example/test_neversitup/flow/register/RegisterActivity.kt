package com.example.test_neversitup.flow.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.test_neversitup.R
import com.example.test_neversitup.base.BaseActivity
import com.example.test_neversitup.databinding.ActivityRegisterBinding
import com.example.test_neversitup.extention.alertDialog
import com.example.test_neversitup.extention.toast
import com.example.test_neversitup.extention.validateEmail
import com.example.test_neversitup.flow.register.model.RegisterRequestModel
import com.example.test_neversitup.flow.register.viewmodel.RegisterViewModel

class RegisterActivity :
    BaseActivity<ActivityRegisterBinding, RegisterViewModel>(RegisterViewModel::class.java),
    View.OnClickListener {

    override var layoutResource = R.layout.activity_register

    override fun initInstance() {
        super.initInstance()
        binding.btRegister.setOnClickListener(this)
    }

    override fun subscribeLiveData() {
        super.subscribeLiveData()
        viewModel.registerMutableLiveData.observe(this, {
            toast("สมัครสมาชิกเสร็จสิ้น")
            finish()
        })
    }

    private fun validateInputData() {
        binding.apply {
            when {
                edUsername.text.toString().isNullOrEmpty() -> {
                    alertDialog("ขออภัย", "กรุณากรอกชื่อผู้ใช้")
                    return
                }
                edEmail.text.toString().validateEmail() -> {
                    alertDialog("ขออภัย", "กรุณากรอกอีเมลที่ถูกต้อง")
                    return
                }
                edPassword.text.toString().isNullOrEmpty() -> {
                    alertDialog("ขออภัย", "กรุณากรอกรหัส")
                    return
                }
                edConfirmPassword.text.toString() != edPassword.text.toString() -> {
                    alertDialog("ขออภัย", "กรุณากรอกรหัสยืนยันที่ถูกต้อง")
                    return
                }
                edAge.text.toString().isNullOrEmpty() -> {
                    alertDialog("ขออภัย", "กรุณากรอกอายุ")
                    return
                }
                else -> {
                    viewModel.register(
                        RegisterRequestModel(
                            name = edUsername.text.toString(),
                            email = edEmail.text.toString(),
                            password = edPassword.text.toString(),
                            age = edAge.text.toString().toInt()
                        )
                    )
                    return
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btRegister -> {
                validateInputData()
            }
        }
    }
}