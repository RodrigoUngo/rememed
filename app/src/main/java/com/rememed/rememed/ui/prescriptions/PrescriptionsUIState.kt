package com.rememed.rememed.ui.prescriptions

import androidx.lifecycle.LiveData
import com.rememed.rememed.data.models.Prescription
import java.lang.Exception

sealed class PrescriptionsUIState {
    object Loading: PrescriptionsUIState()
    class Error(val exception: Exception): PrescriptionsUIState()
    data class Success(val prescription: LiveData<List<Prescription>>): PrescriptionsUIState()
}