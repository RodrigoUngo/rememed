package com.rememed.rememed.ui.prescriptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.repository.PrescriptionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrescriptionViewModel(private val repository: PrescriptionRepository): ViewModel() {
    private val _status = MutableLiveData<PrescriptionsUIState>(PrescriptionsUIState.Loading)
    val status: LiveData<PrescriptionsUIState>
        get() = _status
    fun getAllPrescriptions(user: String){
        _status.value = PrescriptionsUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(
                when(val response = repository.getAllPrescription(user)){
                    is ApiResponse.Error -> PrescriptionsUIState.Error(response.exception)
                    is ApiResponse.Sucess -> PrescriptionsUIState.Success(response.data)
                    is ApiResponse.ErrorWithMessage -> TODO()
                }
            )
        }
    }
}