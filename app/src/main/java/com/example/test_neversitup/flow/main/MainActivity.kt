package com.example.test_neversitup.flow.main

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_neversitup.R
import com.example.test_neversitup.base.BaseActivity
import com.example.test_neversitup.databinding.ActivityMainBinding
import com.example.test_neversitup.extention.dialogAdd
import com.example.test_neversitup.extention.dialogEdit
import com.example.test_neversitup.extention.savePref
import com.example.test_neversitup.extention.toast
import com.example.test_neversitup.flow.login.LoginActivity
import com.example.test_neversitup.flow.main.adapter.TodoRecyclerViewAdapter
import com.example.test_neversitup.flow.main.model.CreateTodoRequestModel
import com.example.test_neversitup.flow.main.model.GetAllTodoResponseModel
import com.example.test_neversitup.flow.main.model.UpdateTodoRequestModel
import com.example.test_neversitup.flow.main.viewmodel.MainViewModel
import com.example.test_neversitup.room.LoginModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class.java),
    View.OnClickListener {

    override var layoutResource = R.layout.activity_main

    var id: Int? = 0
    var token: String? = ""
    var userId: String? = ""
    override fun initInstance() {
        super.initInstance()
        Flowable.fromCallable { appDatabase.customerDao().getCustomerData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.tvName.text = it?.name
                binding.tvEmail.text = it?.email
                id = it?.id
                token = it?.token
                userId = it?.ids
                savePref("login", true)
                viewModel.getAllTodo(token)
            }

        binding.ivAdd.setOnClickListener(this)
        binding.ivLogout.setOnClickListener(this)
    }

    private fun setUpRecyclerView(list: ArrayList<GetAllTodoResponseModel.Data>) {
        binding.rvTodo.apply {
            post {
                val layoutManager = LinearLayoutManager(context)
                layoutManager.orientation = RecyclerView.VERTICAL
                this.layoutManager = layoutManager
                val adapter =
                    TodoRecyclerViewAdapter(list,
                        { data ->
                            //Edit
                            viewModel.getTodo(token, data?.id)
                        }, { data ->
                            //Delete
                            viewModel.deleteTodo(token, data?.id)
                        })
                adapter.notifyDataSetChanged()
                this.adapter = adapter
            }
        }
    }

    override fun subscribeLiveData() {
        super.subscribeLiveData()
        viewModel.logoutMutableLiveData.observe(this, {
            Flowable.fromCallable { appDatabase.customerDao().deleteTable() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    savePref("login", false)
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
        })

        viewModel.createTodoMutableLiveData.observe(this, {
            toast("เพิ่มสำเร็จ")
            viewModel.getAllTodo(token)
        })

        viewModel.getAllTodoMutableLiveData.observe(this, {
            it?.data?.data?.let { list -> setUpRecyclerView(list) }
        })

        viewModel.getTodoMutableLiveData.observe(this, {
            dialogEdit(it.data?.data) { description, completed ->
                viewModel.updateTodo(
                    token,
                    UpdateTodoRequestModel(description = description, completed = completed)
                )
            }
        })

        viewModel.updateTodoMutableLiveData.observe(this, {
            toast("แก้ไขสำเร็จ")
            viewModel.getAllTodo(token)
        })

        viewModel.deleteTodoMutableLiveData.observe(this, {
            toast("ลบสำเร็จ")
            viewModel.getAllTodo(token)
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivLogout -> {
                viewModel.logout(token)
            }
            R.id.ivAdd -> {
                dialogAdd { description ->
                    viewModel.createTodo(token, CreateTodoRequestModel(description = description))
                }
            }
        }
    }
}