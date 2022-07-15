package com.rememed.rememed.ui.addsymptoms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.repository.SymptomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddSymptomsViewModel(private val repository: SymptomRepository): ViewModel() {

    val sdf = SimpleDateFormat("dd/M/yyyy")
    val date = sdf.format(Date())
    var textField = MutableLiveData("")

    private val _status = MutableLiveData<AddSymptomsUIState>(AddSymptomsUIState.Resume)
    val status: LiveData<AddSymptomsUIState>
        get() = _status

    fun onSave(user: String){
        _status.value = AddSymptomsUIState.Loading
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(
                when(val response = repository.saveSymptom(
                    date.toString(),
                    textField.value.toString(),
                    user
                )){
                    is ApiResponse.Error -> AddSymptomsUIState.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> AddSymptomsUIState.Resume
                    is ApiResponse.Sucess -> AddSymptomsUIState.Sucess(response.data)
                }
            )
        }
    }
}