package com.rememed.rememed.ui.managers

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
import com.rememed.rememed.adapter.ManagerAdapter
import com.rememed.rememed.databinding.ActivityManagersBinding
import com.rememed.rememed.ui.addmanagers.AddManagers

class Managers : AppCompatActivity() {
    private lateinit var binding: ActivityManagersBinding
    val app by lazy{
        application as RemeMedApplication
    }

    private val viewModelFactory by lazy {
        ViewModelFactory(app.getManagerRepository())
    }

    private val viewModel: ManagerViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_managers)

        val managerListRecyclerView = binding.managerListRecyclerView
        val managerAdapter = ManagerAdapter()
        managerListRecyclerView.apply {
            adapter = managerAdapter
        }

        val user = app.getUsername()
        viewModel.getAllManagers(user)

        viewModel.status.observe(this){ status ->
            handleUiState(status, managerAdapter)

        }

        val btnGoBack: ImageButton = findViewById(R.id.ibBack)
        btnGoBack.setOnClickListener{
            finish()
        }

        val btnAddManagers: Button = findViewById(R.id.btnAddManagers)
        btnAddManagers.setOnClickListener{
            Intent(this, AddManagers::class.java).also {
                startActivity(it)
            }
        }

    }

    private fun handleUiState(status: ManagerUIState, managerAdapter: ManagerAdapter){
        when(status){
            is ManagerUIState.Error -> Log.d("Doctor List Staus","Error",status.exception)
            ManagerUIState.Loading -> Log.d("Doctor List Staus","Loading")
            is ManagerUIState.Success -> status.manager.observe(this){ data ->
                managerAdapter.setData(data)
            }
        }
    }
}