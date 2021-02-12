package com.example.test_neversitup.flow.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test_neversitup.R
import com.example.test_neversitup.databinding.ItemListBinding
import com.example.test_neversitup.flow.main.model.GetAllTodoResponseModel

class TodoRecyclerViewAdapter(
    val list: ArrayList<GetAllTodoResponseModel.Data>,
    val itemClickEdit: (GetAllTodoResponseModel.Data?) -> Unit,
    val itemClickDelete: (GetAllTodoResponseModel.Data?) -> Unit
) :
    RecyclerView.Adapter<TodoRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list, parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView()
        list?.get(position)?.let { holder.setResource(it) }
        holder.binding?.apply {
            ivEdit.setOnClickListener {
                itemClickEdit.invoke(list?.get(position))
            }
            ivDelete.setOnClickListener {
                itemClickDelete.invoke(list?.get(position))
            }
        }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemListBinding? = null

        fun bindView() {
            super.itemView
            binding = DataBindingUtil.bind(itemView)

        }

        fun setResource(data: GetAllTodoResponseModel.Data){
            binding?.tvText1?.text = data.description
            binding?.tvText2?.text = "Completed ${data.completed.toString()}"
        }
    }

}