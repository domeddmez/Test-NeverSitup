package com.example.test_neversitup.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.test_neversitup.room.AppDatabase

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(private val clazz: Class<VM>) : AppCompatActivity() {
    lateinit var binding: B
    abstract var layoutResource: Int
    lateinit var viewModel: VM
    lateinit var appDatabase : AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appDatabase = AppDatabase.getAppDatabase(this)

        binding = DataBindingUtil.setContentView(this, layoutResource)
        viewModel = ViewModelProviders.of(this).get(clazz)
        initInstance()
        subscribeLiveData()

        viewModel.activity = this

    }

    open fun initInstance() {

    }

    open fun subscribeLiveData() {

    }
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val v = currentFocus
        if (v != null &&
            (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) &&
            v is EditText &&
            !v.javaClass.name.startsWith("android.webkit.")
        ) {
            val scrcoords = IntArray(2)
            v.getLocationOnScreen(scrcoords)
            val x = ev.rawX + v.getLeft() - scrcoords[0]
            val y = ev.rawY + v.getTop() - scrcoords[1]
            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                hideKeyboard(this, v)
                v.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    open fun hideKeyboard(activity: Activity?, v: View?) {
        if (activity != null && activity.window != null && activity.window.decorView != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
        }
    }

}