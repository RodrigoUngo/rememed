package com.rememed.rememed.ui.register

import java.lang.Exception

sealed class RegisterUIStatus {
    object Resume: RegisterUIStatus()
    object Loading: RegisterUIStatus()
    class Error(val exception: Exception): RegisterUIStatus()
    data class Sucess(val token: String?): RegisterUIStatus()
}