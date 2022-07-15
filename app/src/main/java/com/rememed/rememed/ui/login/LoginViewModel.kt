package com.rememed.rememed.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rememed.rememed.network.ApiResponse
import com.rememed.rememed.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    val userField = MutableLiveData("")
    val passwordField = MutableLiveData("")

    private val _status = MutableLiveData<LoginUiStatus>(LoginUiStatus.Resume)
    val status: LiveData<LoginUiStatus>
        get() = _status

    fun onLogin(){
        _status.value = LoginUiStatus.Loading
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(
                when(val response = repository.login(
                    userField.value.toString(),
                    passwordField.value.toString()
                )){
                    is ApiResponse.Error -> LoginUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> LoginUiStatus.Resume
                    is ApiResponse.Sucess -> LoginUiStatus.Sucess(response.data)
                }
            )
        }
    }
}