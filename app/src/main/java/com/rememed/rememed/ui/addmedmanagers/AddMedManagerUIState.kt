package com.rememed.rememed.ui.addmedmanagers

import java.lang.Exception

sealed class AddMedManagerUIState {
    object Resume: AddMedManagerUIState()
    object Loading: AddMedManagerUIState()
    class Error(val exception: Exception): AddMedManagerUIState()
    data class Sucess(val token: String?): AddMedManagerUIState()
}