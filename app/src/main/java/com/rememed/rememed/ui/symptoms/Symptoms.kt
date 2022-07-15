package com.rememed.rememed.ui.symptoms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rememed.rememed.R
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.ViewModelFactory
import com.rememed.rememed.adapter.SymptomAdapter
import com.rememed.rememed.databinding.ActivitySymptomsBinding

class Symptoms : AppCompatActivity() {
    private lateinit var binding: ActivitySymptomsBinding
    val app by lazy{
        application as RemeMedApplication
    }

    private val viewModelFactory by lazy {
        ViewModelFactory(app.getSymptomRepository())
    }

    private val viewModel: SymptomsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_symptoms)

        val symptomListRecyclerView = binding.symptomListRecyclerView
        val symptomAdapter = SymptomAdapter()
        symptomListRecyclerView.apply {
            adapter = symptomAdapter
        }

        val user = app.getUsername()
        viewModel.getAllSymptoms(user)

        viewModel.status.observe(this){ status ->
            handleUiState(status, symptomAdapter)

        }

        val btnGoBack: ImageButton = findViewById(R.id.ibBack)
        btnGoBack.setOnClickListener{
            finish()
        }

    }

    private fun handleUiState(status: SymptomsUIState, symptomAdapter: SymptomAdapter){
        when(status){
            is SymptomsUIState.Error -> Log.d("Doctor List Staus","Error",status.exception)
            SymptomsUIState.Loading -> Log.d("Doctor List Staus","Loading")
            is SymptomsUIState.Success -> status.manager.observe(this){ data ->
                symptomAdapter.setData(data)
            }
        }
    }
}