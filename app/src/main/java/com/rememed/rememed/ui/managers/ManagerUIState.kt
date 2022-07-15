package com.rememed.rememed.ui.managers

import androidx.lifecycle.LiveData
import com.rememed.rememed.data.models.Manager
import java.lang.Exception

sealed class ManagerUIState() {
    object Loading: ManagerUIState()
    class Error(val exception: Exception): ManagerUIState()
    data class Success(val manager: LiveData<List<Manager>>): ManagerUIState()
}