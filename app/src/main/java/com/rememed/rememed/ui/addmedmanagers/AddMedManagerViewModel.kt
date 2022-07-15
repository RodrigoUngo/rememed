package com.rememed.rememed.ui.addmedmanagers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.repository.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddMedManagerViewModel(private val repository: DoctorRepository): ViewModel() {

    var nameField = MutableLiveData("")
    var emailField = MutableLiveData("")
    var phoneField = MutableLiveData("")
    var hospitalField = MutableLiveData("")

    private val _status = MutableLiveData<AddMedManagerUIState>(AddMedManagerUIState.Resume)
    val status: LiveData<AddMedManagerUIState>
        get() = _status

    fun onSave(user: String){
        _status.value = AddMedManagerUIState.Loading
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(
                when(val response = repository.saveDoctor(
                    nameField.value.toString(),
                    emailField.value.toString(),
                    phoneField.value.toString(),
                    hospitalField.value.toString(),
                    user
                )){
                    is ApiResponse.Error -> AddMedManagerUIState.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> AddMedManagerUIState.Resume
                    is ApiResponse.Sucess -> AddMedManagerUIState.Sucess(response.data)
                }
            )
        }
    }
}