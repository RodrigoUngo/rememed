package com.rememed.rememed.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.rememed.rememed.*
import com.rememed.rememed.databinding.ActivityMainBinding
import com.rememed.rememed.ui.register.Register

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val app by lazy{
        application as RemeMedApplication
    }
    private val viewModelFactory by lazy {
        ViewModelFactory(app.getUserRepository())
    }

    private val viewModel: LoginViewModel  by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(app.isUserLogin()){
            return startMainMenu()
        }
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel

        binding.etpUsername.doAfterTextChanged { s ->
            viewModel.userField.value = s.toString()
        }

        binding.etpPassword.doAfterTextChanged { s ->
            viewModel.passwordField.value = s.toString()
        }

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener{
            Intent(this, Register::class.java).also {
                startActivity(it)
            }
        }

        viewModel.status.observe(this){ status ->
            handleUiState(status)
        }
    }

    private fun handleUiState(status: LoginUiStatus){
        when(status){
            is LoginUiStatus.Error -> Log.d("Login List Status","Error")
            LoginUiStatus.Loading -> Log.d("Login List Status","Loading")
            LoginUiStatus.Resume -> Log.d("Login List Status","Resume")
            is LoginUiStatus.Sucess -> {
                app.saveAuthToken(status.token)
                app.saveUsername(viewModel.userField.value.toString())
                startMainMenu()
            }
        }
    }

    private fun startMainMenu(){
        val intent = Intent(this,MainMenu::class.java)
        startActivity(intent)
        finish()
    }
}