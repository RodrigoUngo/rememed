package com.rememed.rememed.ui.symptoms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.repository.ManagerRepository
import com.rememed.rememed.repository.SymptomRepository
import com.rememed.rememed.ui.managers.ManagerUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SymptomsViewModel(private val repository: SymptomRepository): ViewModel() {
    private val _status = MutableLiveData<SymptomsUIState>(SymptomsUIState.Loading)
    val status: LiveData<SymptomsUIState>
        get() = _status

    fun getAllSymptoms(user: String){
        _status.value = SymptomsUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(
                when(val response = repository.getAllSymptoms(user)){
                    is ApiResponse.Error -> SymptomsUIState.Error(response.exception)
                    is ApiResponse.Sucess -> SymptomsUIState.Success(response.data)
                    is ApiResponse.ErrorWithMessage -> TODO()
                }
            )
        }
    }


}