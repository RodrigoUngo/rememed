package com.rememed.rememed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rememed.rememed.R
import com.rememed.rememed.data.models.Manager
import com.rememed.rememed.data.models.Symptom
import com.rememed.rememed.databinding.ItemManagersBinding
import com.rememed.rememed.databinding.ItemSymptomsBinding

class SymptomAdapter : RecyclerView.Adapter<SymptomAdapter.SymptomViewHolder>() {
    private var symptoms: List<Symptom>? = null

    fun setData(data: List<Symptom>) {
        symptoms = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = symptoms?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SymptomViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_symptoms,
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: SymptomViewHolder, position: Int) {
        symptoms?.let {
            holder.bind(it[position])
        }
    }

    inner class SymptomViewHolder(private val binding: ItemSymptomsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(symptom: Symptom) {
            binding.symptom = symptom
            binding.executePendingBindings()
        }

    }

}