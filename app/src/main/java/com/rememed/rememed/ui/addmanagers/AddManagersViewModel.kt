package com.rememed.rememed.ui.addmanagers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rememed.rememed.RemeMedApplication
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.repository.ManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddManagersViewModel(private val repository: ManagerRepository): ViewModel() {

    var nameField = MutableLiveData("")
    var emailField = MutableLiveData("")
    var phoneField = MutableLiveData("")

    private val _status = MutableLiveData<AddManagersUIState>(AddManagersUIState.Resume)
    val status: LiveData<AddManagersUIState>
        get() = _status

    fun onSave(user: String){
        _status.value = AddManagersUIState.Loading
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(
                when(val response = repository.saveManager(
                    nameField.value.toString(),
                    emailField.value.toString(),
                    phoneField.value.toString(),
                    user
                )){
                    is ApiResponse.Error -> AddManagersUIState.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> AddManagersUIState.Resume
                    is ApiResponse.Sucess -> AddManagersUIState.Sucess(response.data)
                }
            )
        }
    }
}