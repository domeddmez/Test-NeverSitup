package com.example.test_neversitup.extention

import android.app.Activity
import android.app.ProgressDialog
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.test_neversitup.R
import com.example.test_neversitup.databinding.DialogAddBinding
import com.example.test_neversitup.databinding.DialogEditBinding
import com.example.test_neversitup.flow.main.model.GetAllTodoResponseModel
import com.example.test_neversitup.flow.main.model.GetTodoResponseModel

private var progressDialog: ProgressDialog? = null

fun Activity.showProgressDialog() {
    progressDialog?.let {
        it.dismiss()
    }
    progressDialog = ProgressDialog(this)
    progressDialog?.setMessage("กรุณารอซักครู่...")
    progressDialog?.show()
}

fun Activity.dismissDialog() {
    progressDialog?.let {
        it.dismiss()
    }
}

fun Activity.alertDialog(title: String?, message: String?) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .show()
}

fun String.validateEmail(): Boolean {
    val pattern =
        "^[a-zA-Z0-9._-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\" +
                "-0-9]+\\.)+[a-zA-Z]{2,}))$"
    return !trim().matches(pattern.toRegex())
}

fun Activity.toast(message :String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Activity.savePref(key: String, keyValue: Any) {
    when (keyValue) {
        is String -> {
            getSharedPref().edit().putString(key, keyValue).apply()
        }
        is Int -> {
            getSharedPref().edit().putInt(key, keyValue).apply()
        }

        is Boolean -> {
            getSharedPref().edit().putBoolean(key, keyValue).apply()
        }
    }

}

fun Activity.getPreferString(key: String): String = getSharedPref().getString(key, "") ?: ""

fun Activity.getPreferInt(key: String): Int = getSharedPref().getInt(key, 0)

fun Activity.getPreferBoolean(key: String): Boolean = getSharedPref().getBoolean(key, false)

fun Activity.deleteSharedPref(key: String) = getSharedPref().edit().remove(key).apply()

fun Activity.getSharedPref(): SharedPreferences =
    this.getSharedPreferences("TEST", Activity.MODE_PRIVATE)

fun Activity.dialogAdd(
    itemClickAdd: (String) -> Unit
) {
    var dialog: AlertDialog? = null

    val builder = AlertDialog.Builder(this)
    val binding =
        DataBindingUtil.inflate<DialogAddBinding>(
            LayoutInflater.from(this),
            R.layout.dialog_add,
            null,
            false
        )
    builder.apply {
        setView(binding.root)
        setCancelable(false)
    }

    binding?.apply {
        btAdd.setOnClickListener {
            if (binding.edDescription.text.toString().isNullOrEmpty()) {
                dialog?.dismiss()
            } else {
                itemClickAdd.invoke(binding.edDescription.text.toString())
                dialog?.dismiss()
            }
        }


    }.run {
        dialog = builder.create()
        dialog?.window?.attributes?.windowAnimations = R.style.DialogScale
        dialog?.show()
    }
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
}

fun Activity.dialogEdit(
    data: GetTodoResponseModel.Data?,
    itemClickEdit: (String, Boolean) -> Unit
) {
    var dialog: AlertDialog? = null

    val builder = AlertDialog.Builder(this)
    val binding =
        DataBindingUtil.inflate<DialogEditBinding>(
            LayoutInflater.from(this),
            R.layout.dialog_edit,
            null,
            false
        )
    builder.apply {
        setView(binding.root)
        setCancelable(false)
    }

    binding?.apply {
        edDescription.setText(data?.description.toString())
        binding.rdTrue.isChecked = true

        if (data?.completed == true) {
            binding.rdTrue.isChecked = true
        } else {
            binding.rdFalse.isChecked = true
        }

        btEdit.setOnClickListener {
            if (rdTrue.isChecked) {
                itemClickEdit.invoke(edDescription.text.toString(),true)
                dialog?.dismiss()
            } else {
                itemClickEdit.invoke(edDescription.text.toString(),false)
                dialog?.dismiss()
            }
        }


    }.run {
        dialog = builder.create()
        dialog?.window?.attributes?.windowAnimations = R.style.DialogScale
        dialog?.show()
    }
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
}