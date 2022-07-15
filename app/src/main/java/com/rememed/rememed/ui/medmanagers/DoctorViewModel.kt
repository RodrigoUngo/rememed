package com.rememed.rememed.ui.medmanagers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.repository.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DoctorViewModel(private val repository: DoctorRepository): ViewModel() {
    private val _status = MutableLiveData<DoctorUIState>(DoctorUIState.Loading)
    val status: LiveData<DoctorUIState>
        get() = _status
    fun getAllDoctors(user: String){
        _status.value = DoctorUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(
                when(val response = repository.getAllDoctors(user)){
                    is ApiResponse.Error -> DoctorUIState.Error(response.exception)
                    is ApiResponse.Sucess -> DoctorUIState.Success(response.data)
                    is ApiResponse.ErrorWithMessage -> TODO()
                }
            )
        }
    }


}