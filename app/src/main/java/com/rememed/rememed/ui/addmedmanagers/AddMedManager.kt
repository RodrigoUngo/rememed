package com.rememed.rememed.ui.addmedmanagers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.rememed.rememed.ui.medmanagers.MedManager
import com.rememed.rememed.R
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.ViewModelFactory
import com.rememed.rememed.databinding.ActivityAddMedManagerBinding
import com.rememed.rememed.ui.symptoms.Symptoms

class AddMedManager : AppCompatActivity() {
    private lateinit var binding: ActivityAddMedManagerBinding
    val app by lazy{
        application as RemeMedApplication
    }
    private val viewModelFactory by lazy {
        ViewModelFactory(app.getDoctorRepository())
    }

    private val viewModel: AddMedManagerViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_med_manager)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_med_manager)
        binding.viewModel = viewModel

        binding.etMedManagerName.doAfterTextChanged { s ->
            viewModel.nameField.value = s.toString()
        }

        binding.etMedManagerEmail.doAfterTextChanged { s ->
            viewModel.emailField.value = s.toString()
        }

        binding.etMedManagerPhone.doAfterTextChanged { s ->
            viewModel.phoneField.value = s.toString()
        }

        binding.etJobPlace.doAfterTextChanged { s ->
            viewModel.hospitalField.value = s.toString()
        }

        viewModel.status.observe(this){ status ->
            handleUiState(status)

        }

        val user = app.getUsername()
        val btnSaveManager: Button = findViewById(R.id.btnAdd)
        btnSaveManager.setOnClickListener {
            viewModel.onSave(user)
        }

        val btnGoBack: ImageButton = findViewById(R.id.ibBack)
        btnGoBack.setOnClickListener{
            finish()
        }

    }

    private fun handleUiState(status: AddMedManagerUIState){
        when(status){
            is AddMedManagerUIState.Error -> Log.d("Login List Status","Error")
            AddMedManagerUIState.Loading -> Log.d("Login List Status","Loading")
            AddMedManagerUIState.Resume -> Log.d("Login List Status","Resume")
            is AddMedManagerUIState.Sucess -> {
                Intent(this, MedManager::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }
}