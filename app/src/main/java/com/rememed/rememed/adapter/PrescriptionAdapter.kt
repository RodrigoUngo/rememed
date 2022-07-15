package com.rememed.rememed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rememed.rememed.R
import com.rememed.rememed.data.models.Doctor
import com.rememed.rememed.data.models.Prescription
import com.rememed.rememed.databinding.ItemMedManagersBinding
import com.rememed.rememed.databinding.ItemPrescriptionsBinding

class PrescriptionAdapter : RecyclerView.Adapter<PrescriptionAdapter.PrescriptionViewHolder>() {
    private var prescriptions: List<Prescription>? = null

    fun setData(data: List<Prescription>) {
        prescriptions = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = prescriptions?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PrescriptionViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_prescriptions,
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: PrescriptionViewHolder, position: Int) {
        prescriptions?.let {
            holder.bind(it[position])
        }
    }

    inner class PrescriptionViewHolder(private val binding: ItemPrescriptionsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(prescription: Prescription) {
            binding.prescription = prescription
            binding.executePendingBindings()
        }

    }

}