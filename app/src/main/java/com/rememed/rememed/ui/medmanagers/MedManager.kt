package com.rememed.rememed.ui.medmanagers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.rememed.rememed.R
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.ViewModelFactory
import com.rememed.rememed.adapter.DoctorAdapter
import com.rememed.rememed.databinding.ActivityMedManagerBinding
import com.rememed.rememed.ui.addmedmanagers.AddMedManager

class MedManager : AppCompatActivity() {
    private lateinit var binding: ActivityMedManagerBinding
    val app by lazy{
        application as RemeMedApplication
    }

    private val viewModelFactory by lazy {
        ViewModelFactory(app.getDoctorRepository())
    }

    private val viewModel: DoctorViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_med_manager)

        val btnAddMedManagers: Button = findViewById(R.id.btnAddMedManagers)
        btnAddMedManagers.setOnClickListener{
            Intent(this, AddMedManager::class.java).also {
                startActivity(it)
            }
        }

        val btnGoBack: ImageButton = findViewById(R.id.ibBack)
        btnGoBack.setOnClickListener{
            finish()
        }

        val doctorListRecyclerView = binding.doctorListRecyclerView
        val doctorAdapter = DoctorAdapter()
        doctorListRecyclerView.apply {
            adapter = doctorAdapter
        }

        val user = app.getUsername()
        viewModel.getAllDoctors(user)

        viewModel.status.observe(this){ status ->
            handleUiState(status, doctorAdapter)

        }
    }

    private fun handleUiState(status: DoctorUIState, doctorAdapter: DoctorAdapter){
        when(status){
            is DoctorUIState.Error -> Log.d("Doctor List Staus","Error",status.exception)
            DoctorUIState.Loading -> Log.d("Doctor List Staus","Loading")
            is DoctorUIState.Success -> status.doctor.observe(this){ data ->
                doctorAdapter.setData(data)
            }
        }
    }
}