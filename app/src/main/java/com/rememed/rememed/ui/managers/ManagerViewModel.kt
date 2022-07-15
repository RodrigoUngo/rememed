package com.rememed.rememed.ui.managers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.repository.ManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ManagerViewModel(private val repository: ManagerRepository): ViewModel() {
    private val _status = MutableLiveData<ManagerUIState>(ManagerUIState.Loading)
    val status: LiveData<ManagerUIState>
        get() = _status

    fun getAllManagers(user: String){
        _status.value = ManagerUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(
                when(val response = repository.getAllManagers(user)){
                    is ApiResponse.Error -> ManagerUIState.Error(response.exception)
                    is ApiResponse.Sucess -> ManagerUIState.Success(response.data)
                    is ApiResponse.ErrorWithMessage -> TODO()
                }
            )
        }
    }


}