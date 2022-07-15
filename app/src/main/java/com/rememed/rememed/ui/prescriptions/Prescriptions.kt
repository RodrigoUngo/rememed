package com.rememed.rememed.ui.prescriptions

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
import com.rememed.rememed.adapter.PrescriptionAdapter
import com.rememed.rememed.databinding.ActivityMedManagerBinding
import com.rememed.rememed.databinding.ActivityPrescriptionsBinding
import com.rememed.rememed.ui.addprescriptions.AddNewPrescription
import com.rememed.rememed.ui.medmanagers.DoctorUIState
import com.rememed.rememed.ui.medmanagers.DoctorViewModel

class Prescriptions : AppCompatActivity() {
    private lateinit var binding: ActivityPrescriptionsBinding
    val app by lazy{
        application as RemeMedApplication
    }

    private val viewModelFactory by lazy {
        ViewModelFactory(app.getPrescriptionRepository())
    }

    private val viewModel: PrescriptionViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_prescriptions)

        val btnAddPrescription: Button = findViewById(R.id.btnAddPrescription)
        btnAddPrescription.setOnClickListener{
            Intent(this, AddNewPrescription::class.java).also {
                startActivity(it)
            }
        }

        val btnGoBack: ImageButton = findViewById(R.id.ibBack)
        btnGoBack.setOnClickListener{
            finish()
        }

        val prescriptionListRecyclerView = binding.rvPrescriptionsList
        val prescriptionAdapter = PrescriptionAdapter()
        prescriptionListRecyclerView.apply {
            adapter = prescriptionAdapter
        }

        val user = app.getUsername()
        viewModel.getAllPrescriptions(user)

        viewModel.status.observe(this){ status ->
            handleUiState(status, prescriptionAdapter)

        }
    }

    private fun handleUiState(status: PrescriptionsUIState, prescriptionAdapter: PrescriptionAdapter){
        when(status){
            is PrescriptionsUIState.Error -> Log.d("Prescription List Staus","Error",status.exception)
            PrescriptionsUIState.Loading -> Log.d("Prescription List Staus","Loading")
            is PrescriptionsUIState.Success -> status.prescription.observe(this){ data ->
                prescriptionAdapter.setData(data)
            }
        }
    }
}