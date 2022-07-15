package com.rememed.rememed.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    var userField = MutableLiveData("")
    var nameField = MutableLiveData("")
    var lastnameField = MutableLiveData("")
    var emailField = MutableLiveData("")
    var passwordField = MutableLiveData("")
    var confirmPasswordField = MutableLiveData("")

    private val _status = MutableLiveData<RegisterUIStatus>(RegisterUIStatus.Resume)
    val status: LiveData<RegisterUIStatus>
        get() = _status

    fun onRegister(){
        _status.value = RegisterUIStatus.Loading
        if (passwordField.value.toString() != confirmPasswordField.value.toString()){
            return
        }
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(
                when(val response = repository.register(
                    userField.value.toString(),
                    nameField.value.toString(),
                    lastnameField.value.toString(),
                    emailField.value.toString(),
                    passwordField.value.toString()
                )){
                    is ApiResponse.Error -> RegisterUIStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> RegisterUIStatus.Resume
                    is ApiResponse.Sucess -> RegisterUIStatus.Sucess(response.data)
                }
            )
        }
    }
}