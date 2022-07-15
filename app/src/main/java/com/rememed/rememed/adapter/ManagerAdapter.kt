package com.rememed.rememed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rememed.rememed.R
import com.rememed.rememed.data.models.Doctor
import com.rememed.rememed.data.models.Manager
import com.rememed.rememed.databinding.ItemManagersBinding
import com.rememed.rememed.databinding.ItemMedManagersBinding

class ManagerAdapter : RecyclerView.Adapter<ManagerAdapter.ManagerViewHolder>() {
    private var managers: List<Manager>? = null

    fun setData(data: List<Manager>) {
        managers = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = managers?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ManagerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_managers,
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: ManagerViewHolder, position: Int) {
        managers?.let {
            holder.bind(it[position])
        }
    }

    inner class ManagerViewHolder(private val binding: ItemManagersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(manager: Manager) {
            binding.manager = manager
            binding.executePendingBindings()
        }

    }

}