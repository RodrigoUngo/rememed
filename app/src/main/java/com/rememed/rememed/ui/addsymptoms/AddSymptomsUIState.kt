package com.rememed.rememed.ui.addsymptoms

import java.lang.Exception

sealed class AddSymptomsUIState {
    object Resume: AddSymptomsUIState()
    object Loading: AddSymptomsUIState()
    class Error(val exception: Exception): AddSymptomsUIState()
    data class Sucess(val token: String?): AddSymptomsUIState()
}