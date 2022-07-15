package com.rememed.rememed.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.rememed.rememed.R
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.ViewModelFactory
import com.rememed.rememed.databinding.ActivityRegisterBinding
import com.rememed.rememed.ui.login.MainActivity

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val app by lazy{
        application as RemeMedApplication
    }
    private val viewModelFactory by lazy {
        ViewModelFactory(app.getUserRepository())
    }

    private val viewModel: RegisterViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel = viewModel

        binding.etRegisterUsername.doAfterTextChanged { s ->
            viewModel.userField.value = s.toString()
        }

        binding.etRegisterName.doAfterTextChanged { s ->
            viewModel.nameField.value = s.toString()
        }

        binding.etRegisterLastname.doAfterTextChanged { s ->
            viewModel.lastnameField.value = s.toString()
        }

        binding.etRegisterEmail.doAfterTextChanged { s ->
            viewModel.emailField.value = s.toString()
        }

        binding.etRegisterPassword.doAfterTextChanged { s ->
            viewModel.passwordField.value = s.toString()
        }

        binding.etRegisterPasswordConfirm.doAfterTextChanged { s ->
            viewModel.confirmPasswordField.value = s.toString()
        }

        viewModel.status.observe(this){ status ->
            handleUiState(status)

        }

        val btnGoBack: ImageButton = findViewById(R.id.ibBack)
        btnGoBack.setOnClickListener{
            finish()
        }
    }

    private fun handleUiState(status: RegisterUIStatus){
        when(status){
            is RegisterUIStatus.Error -> Log.d("Login List Status","Error")
            RegisterUIStatus.Loading -> Log.d("Login List Status","Loading")
            RegisterUIStatus.Resume -> Log.d("Login List Status","Resume")
            is RegisterUIStatus.Sucess -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}