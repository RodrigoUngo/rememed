package com.rememed.rememed.ui.symptoms

import androidx.lifecycle.LiveData
import com.rememed.rememed.data.models.Manager
import com.rememed.rememed.data.models.Symptom
import java.lang.Exception

sealed class SymptomsUIState() {
    object Loading: SymptomsUIState()
    class Error(val exception: Exception): SymptomsUIState()
    data class Success(val manager: LiveData<List<Symptom>>): SymptomsUIState()
}