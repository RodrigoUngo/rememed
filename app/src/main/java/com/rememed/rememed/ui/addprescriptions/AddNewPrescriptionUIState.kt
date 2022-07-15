package com.rememed.rememed.ui.addprescriptions

import java.lang.Exception

sealed class AddNewPrescriptionUIState {
    object Resume: AddNewPrescriptionUIState()
    object Loading: AddNewPrescriptionUIState()
    class Error(val exception: Exception): AddNewPrescriptionUIState()
    data class Sucess(val token: String?): AddNewPrescriptionUIState()
}