package com.rememed.rememed.ui.addsymptoms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.rememed.rememed.MainMenu
import com.rememed.rememed.R
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.ViewModelFactory
import com.rememed.rememed.databinding.ActivityAddManagersBinding
import com.rememed.rememed.databinding.ActivityAddSymptomsBinding
import com.rememed.rememed.ui.addmanagers.AddManagersUIState
import com.rememed.rememed.ui.addmanagers.AddManagersViewModel
import com.rememed.rememed.ui.managers.Managers

class AddSymptoms : AppCompatActivity() {
    private lateinit var binding: ActivityAddSymptomsBinding
    val app by lazy{
        application as RemeMedApplication
    }
    private val viewModelFactory by lazy {
        ViewModelFactory(app.getSymptomRepository())
    }

    private val viewModel: AddSymptomsViewModel by viewModels {
        viewModelFactory
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_symptoms)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_symptoms)
        binding.viewModel = viewModel

        binding.etMedManagerName.doAfterTextChanged { s ->
            viewModel.textField.value = s.toString()
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

    private fun handleUiState(status: AddSymptomsUIState){
        when(status){
            is AddSymptomsUIState.Error -> Log.d("Login List Status","Error")
            AddSymptomsUIState.Loading -> Log.d("Login List Status","Loading")
            AddSymptomsUIState.Resume -> Log.d("Login List Status","Resume")
            is AddSymptomsUIState.Sucess -> {
                finish()
            }
        }
    }
}