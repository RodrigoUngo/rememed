package com.rememed.rememed.ui.addmanagers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.rememed.rememed.ui.managers.Managers
import com.rememed.rememed.R
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.ViewModelFactory
import com.rememed.rememed.databinding.ActivityAddManagersBinding
import com.rememed.rememed.ui.symptoms.Symptoms

class AddManagers : AppCompatActivity() {
    private lateinit var binding: ActivityAddManagersBinding
    val app by lazy{
        application as RemeMedApplication
    }
    private val viewModelFactory by lazy {
        ViewModelFactory(app.getManagerRepository())
    }

    private val viewModel: AddManagersViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_managers)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_managers)
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

    private fun handleUiState(status: AddManagersUIState){
        when(status){
            is AddManagersUIState.Error -> Log.d("Login List Status","Error")
            AddManagersUIState.Loading -> Log.d("Login List Status","Loading")
            AddManagersUIState.Resume -> Log.d("Login List Status","Resume")
            is AddManagersUIState.Sucess -> {
                Intent(this, Managers::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }
}