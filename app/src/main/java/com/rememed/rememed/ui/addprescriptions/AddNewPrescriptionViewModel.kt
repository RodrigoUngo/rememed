package com.rememed.rememed.ui.addprescriptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.repository.PrescriptionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewPrescriptionViewModel(private val repository: PrescriptionRepository) : ViewModel() {
    var dosisField = MutableLiveData("")
    var hoursField = MutableLiveData("")
    var timeField = MutableLiveData("")

    private val _status = MutableLiveData<AddNewPrescriptionUIState>(AddNewPrescriptionUIState.Resume)
    val status: LiveData<AddNewPrescriptionUIState>
        get() = _status

    fun onSave(medicine: String, dosisType: String, timesWeek: String, day: String, food: String, user: String){
        var dosis = dosisField.value.toString() + " " + dosisType
        _status.value = AddNewPrescriptionUIState.Loading
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(
                when(val response = repository.savePrescription(
                    medicine,
                    dosis,
                    timesWeek,
                    day,
                    hoursField.value.toString(),
                    timeField.value.toString(),
                    food,
                    user
                )){
                    is ApiResponse.Error -> AddNewPrescriptionUIState.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> AddNewPrescriptionUIState.Resume
                    is ApiResponse.Sucess -> AddNewPrescriptionUIState.Sucess(response.data)
                }
            )
        }
    }
}