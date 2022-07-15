package com.rememed.rememed.ui.medmanagers

import androidx.lifecycle.LiveData
import com.rememed.rememed.data.models.Doctor
import java.lang.Exception

sealed class DoctorUIState() {
    object Loading: DoctorUIState()
    class Error(val exception: Exception): DoctorUIState()
    data class Success(val doctor: LiveData<List<Doctor>>): DoctorUIState()
}