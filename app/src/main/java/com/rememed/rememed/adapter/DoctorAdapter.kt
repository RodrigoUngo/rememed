package com.rememed.rememed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rememed.rememed.R
import com.rememed.rememed.data.models.Doctor
import com.rememed.rememed.databinding.ItemMedManagersBinding

class DoctorAdapter : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {
    private var doctors: List<Doctor>? = null

    fun setData(data: List<Doctor>) {
        doctors = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = doctors?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DoctorViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_med_managers,
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        doctors?.let {
            holder.bind(it[position])
        }
    }

    inner class DoctorViewHolder(private val binding: ItemMedManagersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(doctor: Doctor) {
            binding.doctor = doctor
            binding.executePendingBindings()
        }

    }

}